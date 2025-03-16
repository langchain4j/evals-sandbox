package dev.langchain4j.evals;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.jlama.JlamaEmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;

import java.util.List;

//Had to add JDK_JAVA_OPTIONS=--add-modules jdk.incubator.vector --enable-preview in the environment variables for this to work

public class BertTest {
    public static void main(String[] args) {
        EmbeddingModel embeddingModel = JlamaEmbeddingModel.builder()
                .modelName("sentence-transformers/all-MiniLM-L6-v2")
                .build();

        // For simplicity, this example uses an in-memory store, but you can choose any external compatible store for production environments.
        EmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

        TextSegment segment1 = TextSegment.from("I like football.");
        Embedding embedding1 = embeddingModel.embed(segment1).content();
        embeddingStore.add(embedding1, segment1);

        TextSegment segment2 = TextSegment.from("The weather is good today.");
        Embedding embedding2 = embeddingModel.embed(segment2).content();
        embeddingStore.add(embedding2, segment2);

        String userQuery = "Isn't it cold?";
        Embedding queryEmbedding = embeddingModel.embed(userQuery).content();
        int maxResults = 1;
        List<EmbeddingMatch<TextSegment>> relevant = embeddingStore.findRelevant(queryEmbedding, maxResults);
        EmbeddingMatch<TextSegment> embeddingMatch = relevant.get(0);

        System.out.println("Question: " + userQuery); // What is your favourite sport?
        System.out.println("Response: " + embeddingMatch.embedded().text()); // I like football.
    }
}
