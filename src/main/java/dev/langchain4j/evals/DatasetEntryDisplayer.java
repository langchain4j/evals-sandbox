package dev.langchain4j.evals;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.bgesmallenv15q.BgeSmallEnV15QuantizedEmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DatasetEntryDisplayer {

    public static void main(String[] args) {

        //Load all documents from lc4j documentation.
        //
        List<Document> l4jDocuments = FileSystemDocumentLoader.loadDocumentsRecursively("../langchain4j/docs/docs");

        //Split the documents.
        DocumentSplitter splitter = DocumentSplitters.recursive(1000, 0);
        List<TextSegment> segments = splitter.splitAll(l4jDocuments);

        //Calculate embeddings for them.
        EmbeddingModel embeddingModel = new BgeSmallEnV15QuantizedEmbeddingModel();
        List<Embedding> embeddings = embeddingModel.embedAll(segments).content();

        //Add them to embedding store.
        EmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
        embeddingStore.addAll(embeddings, segments);

        try (FileWriter writer = new FileWriter("output2.txt")) {
            for (DatasetEntry entry : Dataset.get()) {
                writer.write("Query: " + entry.query() + "\n");
                var queryEmbedding = embeddingModel.embed(entry.query()).content();
                var searchRequest = EmbeddingSearchRequest.builder().queryEmbedding(queryEmbedding).maxResults(5).build();
                var searchResult = embeddingStore.search(searchRequest);

                for (EmbeddingMatch match : searchResult.matches()) {
                    TextSegment textSegment = (TextSegment) match.embedded();
                    writer.write("Text: \n" + textSegment.text() + "\n");
                    writer.write("\nMetadata: " + textSegment.metadata() + "\n");
                    writer.write("-------------------------------------------------------\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
