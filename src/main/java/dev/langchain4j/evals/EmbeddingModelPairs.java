package dev.langchain4j.evals;

import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2q.AllMiniLmL6V2QuantizedEmbeddingModel;

import java.util.List;

public class EmbeddingModelPairs {

    static List<EmbeddingModelPair> get() {

//        OpenAiEmbeddingModel openAiAda2 = OpenAiEmbeddingModel.builder()
//                .apiKey(System.getenv("OPENAI_API_KEY"))
//                .modelName("text-embedding-ada-002")
//                .timeout(Duration.ofSeconds(20))
//                .build();
//
//        OpenAiEmbeddingModel openAi3Large = OpenAiEmbeddingModel.builder()
//                .apiKey(System.getenv("OPENAI_API_KEY"))
//                .modelName("text-embedding-3-large")
//                .timeout(Duration.ofSeconds(20))
//                .build();
//
//        OpenAiEmbeddingModel openAi3Small = OpenAiEmbeddingModel.builder()
//                .apiKey(System.getenv("OPENAI_API_KEY"))
//                .modelName("text-embedding-3-small")
//                .timeout(Duration.ofSeconds(20))
//                .build();

//        VertexAiEmbeddingModel vertexAiGecko3 = VertexAiEmbeddingModel.builder()
//                .endpoint(System.getenv("GCP_VERTEXAI_ENDPOINT"))
//                .project(System.getenv("GCP_PROJECT_ID"))
//                .location(System.getenv("GCP_LOCATION"))
//                .publisher("google")
//                .modelName("textembedding-gecko@003")
//                .maxTokensPerBatch(15_000)
//                .build();
//        VertexAiEmbeddingModel vertexAiGecko3Document = VertexAiEmbeddingModel.builder()
//                .endpoint(System.getenv("GCP_VERTEXAI_ENDPOINT"))
//                .project(System.getenv("GCP_PROJECT_ID"))
//                .location(System.getenv("GCP_LOCATION"))
//                .publisher("google")
//                .modelName("textembedding-gecko@003")
//                .taskType(RETRIEVAL_DOCUMENT)
//                .maxTokensPerBatch(15_000)
//                .build();
//        VertexAiEmbeddingModel vertexAiGecko3Query = VertexAiEmbeddingModel.builder()
//                .endpoint(System.getenv("GCP_VERTEXAI_ENDPOINT"))
//                .project(System.getenv("GCP_PROJECT_ID"))
//                .location(System.getenv("GCP_LOCATION"))
//                .publisher("google")
//                .modelName("textembedding-gecko@003")
//                .taskType(RETRIEVAL_QUERY)
//                .maxTokensPerBatch(15_000)
//                .build();

//        VertexAiEmbeddingModel vertexAi4 = VertexAiEmbeddingModel.builder()
//                .project(System.getenv("GCP_PROJECT_ID"))
//                .location("us-central1")
//                .endpoint("us-central1-aiplatform.googleapis.com:443")
//                .publisher("google")
//                .modelName("text-embedding-004")
//                .maxTokensPerBatch(15_000)
//                .build();
//        VertexAiEmbeddingModel vertexAi4Document = VertexAiEmbeddingModel.builder()
//                .project(System.getenv("GCP_PROJECT_ID"))
//                .location("us-central1")
//                .endpoint("us-central1-aiplatform.googleapis.com:443")
//                .publisher("google")
//                .modelName("text-embedding-004")
//                .taskType(RETRIEVAL_DOCUMENT)
//                .maxTokensPerBatch(15_000)
//                .build();
//        VertexAiEmbeddingModel vertexAi4Query = VertexAiEmbeddingModel.builder()
//                .project(System.getenv("GCP_PROJECT_ID"))
//                .location("us-central1")
//                .endpoint("us-central1-aiplatform.googleapis.com:443")
//                .publisher("google")
//                .modelName("text-embedding-004")
//                .taskType(RETRIEVAL_QUERY)
//                .maxTokensPerBatch(15_000)
//                .build();

        return List.of(

//                new EmbeddingModelPair(new BgeSmallEnV15QuantizedEmbeddingModel(), new BgeSmallEnV15QuantizedEmbeddingModel()),
//                new EmbeddingModelPair(new BgeSmallEnV15EmbeddingModel(), new BgeSmallEnV15EmbeddingModel()),
//
                new EmbeddingModelPair(new AllMiniLmL6V2QuantizedEmbeddingModel(), new AllMiniLmL6V2QuantizedEmbeddingModel()),
                new EmbeddingModelPair(new AllMiniLmL6V2EmbeddingModel(), new AllMiniLmL6V2EmbeddingModel())
//
//                new EmbeddingModelPair(new E5SmallV2QuantizedEmbeddingModel(), new E5SmallV2QuantizedEmbeddingModel()),
//                new EmbeddingModelPair(new E5SmallV2EmbeddingModel(), new E5SmallV2EmbeddingModel()),

//                new EmbeddingModelPair(
//                        BedrockTitanEmbeddingModel.builder()
//                                .region(Region.US_EAST_1)
//                                .model(TitanEmbedTextV2.getValue())
//                                .build(),
//                        BedrockTitanEmbeddingModel.builder()
//                                .region(Region.US_EAST_1)
//                                .model(TitanEmbedTextV2.getValue())
//                                .build()
//                ),

//                new EmbeddingModelPair(
//                        MistralAiEmbeddingModel.builder()
//                                .apiKey(System.getenv("MISTRAL_AI_API_KEY"))
//                                .modelName("mistral-embed")
//                                .build(),
//                        MistralAiEmbeddingModel.builder()
//                                .apiKey(System.getenv("MISTRAL_AI_API_KEY"))
//                                .modelName("mistral-embed")
//                                .build()
//                ),
//
//                new EmbeddingModelPair(openAiAda2, openAiAda2),
//                new EmbeddingModelPair(openAi3Large, openAi3Large),
//                new EmbeddingModelPair(openAi3Small, openAi3Small),

//                new EmbeddingModelPair(vertexAiGecko3, vertexAiGecko3),
//                new EmbeddingModelPair(vertexAiGecko3Document, vertexAiGecko3Query),
//                new EmbeddingModelPair(vertexAi4, vertexAi4),
//                new EmbeddingModelPair(vertexAi4Document, vertexAi4Query)

//                new EmbeddingModelPair(
//                        JinaEmbeddingModel.builder()
//                                .apiKey(System.getenv("JINA_API_KEY"))
//                                .modelName("jina-embeddings-v3")
//                                //.task("retrieval.passage") // TODO
//                                .build(),
//                        JinaEmbeddingModel.builder()
//                                .apiKey(System.getenv("JINA_API_KEY"))
//                                .modelName("jina-embeddings-v3")
//                                //.task("retrieval.query") // TODO
//                                .build()
//                ),
//                new EmbeddingModelPair(
//                        CohereEmbeddingModel.builder()
//                                .apiKey(System.getenv("COHERE_API_KEY"))
//                                .modelName("embed-english-v3.0")
//                                .inputType("search_document")
//                                .build(),
//                        CohereEmbeddingModel.builder()
//                                .apiKey(System.getenv("COHERE_API_KEY"))
//                                .modelName("embed-english-v3.0")
//                                .inputType("search_query")
//                                .build()
//                ),
//                new EmbeddingModelPair(
//                        NomicEmbeddingModel.builder()
//                                .apiKey(System.getenv("NOMIC_API_KEY"))
//                                .modelName("nomic-embed-text-v1.5")
//                                .taskType("search_document")
//                                .build(),
//                        NomicEmbeddingModel.builder()
//                                .apiKey(System.getenv("NOMIC_API_KEY"))
//                                .modelName("nomic-embed-text-v1.5")
//                                .taskType("search_query")
//                                .build()
//                ),
//                new EmbeddingModelPair(
//                        VoyageAiEmbeddingModel.builder()
//                                .apiKey(System.getenv("VOYAGE_API_KEY"))
//                                .modelName("voyage-3")
//                                .inputType("document")
//                                .maxSegmentsPerBatch(128)
//                                .build(),
//                        VoyageAiEmbeddingModel.builder()
//                                .apiKey(System.getenv("VOYAGE_API_KEY"))
//                                .modelName("voyage-3")
//                                .inputType("query")
//                                .maxSegmentsPerBatch(128)
//                                .build()
//                )
        );
    }
}
