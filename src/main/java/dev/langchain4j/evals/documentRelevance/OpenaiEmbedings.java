package dev.langchain4j.evals.documentRelevance;

import dev.langchain4j.model.openai.OpenAiEmbeddingModel;

import java.time.Duration;
import java.util.List;

//Similar idea to TF-IDF but in this case instead of calculating the vector with TF-IDF we use an embeding model for it
public class OpenaiEmbedings {
    public static void main(String[] args) {

        OpenAiEmbeddingModel openAiAda2 = OpenAiEmbeddingModel.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .modelName("text-embedding-ada-002")
                .timeout(Duration.ofSeconds(20))
                .build();

        OpenAiEmbeddingModel openAi3Large = OpenAiEmbeddingModel.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .modelName("text-embedding-3-large")
                .timeout(Duration.ofSeconds(20))
                .build();

        OpenAiEmbeddingModel openAi3Small = OpenAiEmbeddingModel.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .modelName("text-embedding-3-small")
                .timeout(Duration.ofSeconds(20))
                .build();

        for (int i = 0; i < Samples.sampleGroundTruths.size(); i++) {
            System.out.println("Run: " + i);

            //Get documents for the scenario and replace any file paths with the content they point to
            List<String> groundTruths = Samples.sampleGroundTruths
                    .get(i)
                    .stream()
                    .map(Samples::fileToContent)
                    .map(String::toLowerCase)
                    .toList();
            List<String> retrievedDocuments = Samples.sampleRetrievedDocuments
                    .get(i)
                    .stream()
                    .map(Samples::fileToContent)
                    .map(String::toLowerCase)
                    .toList();

            List<List<Float>> groundTruthEmbedings = groundTruths.stream().map(entry -> openAi3Large.embed(entry).content().vectorAsList()).toList();
            List<List<Float>> retrievedDocumentEmbedings = retrievedDocuments.stream().map(entry -> openAi3Large.embed(entry).content().vectorAsList()).toList();

            for (List<Float> retrievedDocumentEmbedding : retrievedDocumentEmbedings) {
                System.out.println("Testing Document:\n-------------------\n\n-------------------\n");
                double highestSimilarity = 0.0;
                for (List<Float> groundTruthEmbedding : groundTruthEmbedings) {
                    double calculatedSimilarity = cosineSimilarity(retrievedDocumentEmbedding, groundTruthEmbedding);
                    if (calculatedSimilarity > highestSimilarity) {
                        highestSimilarity = calculatedSimilarity;
                    }
                }
                System.out.println("Highest Similarity: " + highestSimilarity);
            }
        }
    }

    private static double cosineSimilarity(List<Float> embedding1, List<Float> embedding2) {
        double dotProduct = 0.0;
        double norm1 = 0.0;
        double norm2 = 0.0;

        for (int i = 0; i < embedding1.size(); i++) {
            dotProduct += embedding1.get(i) * embedding2.get(i);
            norm1 += Math.pow(embedding1.get(i), 2);
            norm2 += Math.pow(embedding2.get(i), 2);
        }

        return dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2));
    }
}
