package dev.langchain4j.evals;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.Metadata;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.model.openai.OpenAiTokenizer;
import dev.langchain4j.rag.content.Content;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.query.Query;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;

import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import static dev.langchain4j.data.document.splitter.DocumentSplitters.recursive;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;
import static java.util.stream.Collectors.toSet;

public class Evaluator {

    public static final String LC4J_DOCUMENTATION_PATH = "C:\\dev\\repo\\langchain4j_1\\docs\\docs";
    public static final OpenAiTokenizer TOKENIZER = new OpenAiTokenizer(GPT_4_O_MINI);
    public static final int MAX_LENGTH_TOKENS = 8000; // TODO specify per model

    public static void main(String[] args) {

        for (EmbeddingModelPair embeddingModelPair : EmbeddingModelPairs.get()) {

            for (int maxSegmentSizeInTokens : List.of(-1, 100, 300, 600, 1000)) {

                DocumentSplitter documentSplitter;
                String splitterDescription;
                if (maxSegmentSizeInTokens == -1) {
                    documentSplitter = (document) -> List.of(document.toTextSegment()); // no splitting
                    splitterDescription = "no splitting";
                } else {
                    documentSplitter = recursive(maxSegmentSizeInTokens, 0, TOKENIZER);
                    splitterDescription = "recursive splitting " + maxSegmentSizeInTokens;
                }

                eval(
                        documentSplitter,
                        splitterDescription,
                        embeddingModelPair.documentEmbeddingModel(),
                        embeddingModelPair.queryEmbeddingModel()
                );
            }
        }
    }

    public static void eval(
            DocumentSplitter documentSplitter,
            String splitterDescription,
            EmbeddingModel documentEmbeddingModel,
            EmbeddingModel queryEmbeddingModel
    ) {
        System.out.println("=====================================================================");

        if (documentEmbeddingModel instanceof OpenAiEmbeddingModel model) {
            System.out.println(model.modelName() + " and " + splitterDescription);
        } else {
            System.out.println(documentEmbeddingModel.getClass().getSimpleName() + " and " + splitterDescription);
        }

        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**.{md,mdx}");
        List<Document> documents = FileSystemDocumentLoader.loadDocumentsRecursively(LC4J_DOCUMENTATION_PATH, pathMatcher);

        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

        EmbeddingStoreIngestor embeddingStoreIngestor = EmbeddingStoreIngestor.builder()
                .documentTransformer(document -> {

                    String documentText = document.text();

                    Metadata metadata = document.metadata().copy();
                    String relativePath = metadata.getString("absolute_directory_path")
                            .replace(LC4J_DOCUMENTATION_PATH, "")
                            + "\\" + metadata.getString("file_name");
                    metadata.put("relative_path", relativePath);

                    int documentTokenCount = TOKENIZER.estimateTokenCountInText(documentText);
                    metadata.put("whole_document_token_count", documentTokenCount);
                    if (documentTokenCount > MAX_LENGTH_TOKENS) {
                        System.out.printf("WARN: truncating %s from %d to %d tokens%n", metadata.getString("relative_path"), documentTokenCount, MAX_LENGTH_TOKENS);
                        documentText = TOKENIZER.decode(TOKENIZER.encode(documentText).subList(0, MAX_LENGTH_TOKENS));
                        metadata.put("whole_document_token_count", MAX_LENGTH_TOKENS);
                    }

                    return Document.from(documentText, metadata);
                })
                .documentSplitter(documentSplitter)
                .embeddingModel(documentEmbeddingModel)
                .embeddingStore(embeddingStore)
                .build();

        embeddingStoreIngestor.ingest(documents);

        ContentRetriever contentRetriever = new EmbeddingStoreContentRetrieverWithTokenBudget(
                embeddingStore,
                queryEmbeddingModel,
                10_000
        );

        List<DatasetEntry> dataset = Dataset.get();

        validate(dataset, embeddingStore, documentEmbeddingModel);

        List<Double> scores = dataset.stream()
                .map(datasetEntry -> evaluate(datasetEntry, contentRetriever))
                .toList();

        System.out.println("Scores: " + scores);
        System.out.println("Average score: " + scores.stream().mapToDouble(Double::doubleValue).average().getAsDouble());
    }

    private static void validate(List<DatasetEntry> dataset,
                                 EmbeddingStore<TextSegment> embeddingStore,
                                 EmbeddingModel embeddingModel) {

        EmbeddingSearchRequest searchRequest = EmbeddingSearchRequest.builder()
                .queryEmbedding(embeddingModel.embed("test").content())
                .maxResults(Integer.MAX_VALUE)
                .build();

        EmbeddingSearchResult<TextSegment> searchResult = embeddingStore.search(searchRequest);

        Set<String> relativePaths = searchResult.matches().stream()
                .map(it -> it.embedded().metadata().getString("relative_path"))
                .collect(toSet());

        dataset.forEach(entry -> {
            entry.expectedDocumentPaths().forEach((expectedPath, score) -> {
                if (!relativePaths.contains(expectedPath)) {
                    throw new RuntimeException("Can't find path " + expectedPath);
                }
            });
        });
    }

    private static double evaluate(DatasetEntry datasetEntry, ContentRetriever contentRetriever) {

        Query query = Query.from(datasetEntry.query());

        List<Content> contents = contentRetriever.retrieve(query); // TODO consider caching query embeddings

        Set<String> relativePaths = contents.stream()
                .map(it -> it.textSegment().metadata().getString("relative_path"))
                .collect(toSet());

        AtomicReference<Double> score = new AtomicReference<>(0.0);

        datasetEntry.expectedDocumentPaths().forEach((expectedPath, partialScore) -> {
            if (relativePaths.contains(expectedPath)) {
                score.set(score.get() + partialScore);
            }
        });

        if (score.get() <= 1.0) {
            return score.get();
        } else {
            return 1.0;
        }
    }
}
