package dev.langchain4j.evals;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.evals.evaluators.FuzzyMatchingChunkEvaluator;
import dev.langchain4j.evals.evaluators.RougeMatchingChunkEvaluator;
import dev.langchain4j.evals.evaluators.SentenceMatchingEvaluator;
import dev.langchain4j.evals.evaluators.TokenMatchingEvaluator;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.bgesmallenv15q.BgeSmallEnV15QuantizedEmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;

import java.util.HashMap;
import java.util.List;

// Best possible tune at least for the current dataset
// Observed behaviour is as expected
// Eg. If the number of returned results gets decreased recall drops because while most of them are relevant, less of the ground truth content is found.
// Similarly, if the number of returned results is increased recall increases or stays the same since most ground truth chunks get found, but precision drops because there are more chunks of text that are not relevant
public class PrecisionRecallEvaluationDemo {
    public static void main(String[] args) {

        //Load all documents from lc4j documentation.
        //
        List<Document> l4jDocuments = FileSystemDocumentLoader.loadDocumentsRecursively("./langchain4j-docs");

        //Split the documents.
        DocumentSplitter splitter = DocumentSplitters.recursive(4000, 0);
        List<TextSegment> segments = splitter.splitAll(l4jDocuments);

        //Calculate embeddings for them.
        EmbeddingModel embeddingModel = new BgeSmallEnV15QuantizedEmbeddingModel();
        List<Embedding> embeddings = embeddingModel.embedAll(segments).content();

//        EmbeddingModel embeddingModel = OpenAiEmbeddingModel.builder().apiKey(System.getenv("OPENAI_API_KEY")).build();
//        List<Embedding> embeddings = embeddingModel.embedAll(segments).content();

        //Add them to embedding store.
        EmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
        embeddingStore.addAll(embeddings, segments);

        var sentenceMathcingEvaluator = new SentenceMatchingEvaluator();
        var tokenMatchingEvaluator = new TokenMatchingEvaluator();
        var fuzzyMatchingEvaluator = new FuzzyMatchingChunkEvaluator();
        var rougeMatchingEvaluator = new RougeMatchingChunkEvaluator();

        HashMap<String, Double> averageSentenceResults = new HashMap<>();
        HashMap<String, Double> averageTokenResults = new HashMap<>();
        HashMap<String, Double> averageFuzzyResults = new HashMap<>();
        HashMap<String, Double> averageRougeResults = new HashMap<>();

        for (DatasetEntry entry: Dataset.get()){
            var queryEmbedding = embeddingModel.embed(entry.query()).content();
            var searchRequest = EmbeddingSearchRequest.builder().queryEmbedding(queryEmbedding).maxResults(7).build();
            var searchResult = embeddingStore.search(searchRequest);

            var sentenceResults = sentenceMathcingEvaluator.evaluate(entry.expectedContextResults(), searchResult.matches().stream().map(EmbeddingMatch::embedded).toList());
            for (String key: sentenceResults.keySet()){
                averageSentenceResults.put(key, averageSentenceResults.getOrDefault(key, 0.0) + sentenceResults.get(key));
            }

            var tokenResults = tokenMatchingEvaluator.evaluate(entry.expectedContextResults(), searchResult.matches().stream().map(EmbeddingMatch::embedded).toList());
            for (String key: tokenResults.keySet()){
                averageTokenResults.put(key, averageTokenResults.getOrDefault(key, 0.0) + tokenResults.get(key));
            }

            var fuzzyResults = fuzzyMatchingEvaluator.evaluate(entry.expectedContextResults(), searchResult.matches().stream().map(EmbeddingMatch::embedded).toList());
            for (String key: fuzzyResults.keySet()){
                averageFuzzyResults.put(key, averageFuzzyResults.getOrDefault(key, 0.0) + fuzzyResults.get(key));
            }

            var rougeResults = rougeMatchingEvaluator.evaluate(entry.expectedContextResults(), searchResult.matches().stream().map(EmbeddingMatch::embedded).toList());
            for (String key: rougeResults.keySet()){
                averageRougeResults.put(key, averageRougeResults.getOrDefault(key, 0.0) + rougeResults.get(key));
            }
        }

        System.out.println("Average sentence results:");
        for (String key: averageSentenceResults.keySet()){
            System.out.println(key + ": " + averageSentenceResults.get(key) / Dataset.get().size());
        }

        System.out.println("Average token results:");
        for (String key: averageTokenResults.keySet()){
            System.out.println(key + ": " + averageTokenResults.get(key) / Dataset.get().size());
        }

        System.out.println("Average fuzzy results:");
        for (String key: averageFuzzyResults.keySet()){
            System.out.println(key + ": " + averageFuzzyResults.get(key) / Dataset.get().size());
        }

        System.out.println("Average rouge results:");
        for (String key: averageRougeResults.keySet()){
            System.out.println(key + ": " + averageRougeResults.get(key) / Dataset.get().size());
        }
    }
}
