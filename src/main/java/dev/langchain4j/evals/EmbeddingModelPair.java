package dev.langchain4j.evals;

import dev.langchain4j.model.embedding.EmbeddingModel;

record EmbeddingModelPair(EmbeddingModel documentEmbeddingModel, EmbeddingModel queryEmbeddingModel) {

}