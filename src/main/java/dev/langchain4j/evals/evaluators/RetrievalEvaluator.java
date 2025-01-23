package dev.langchain4j.evals.evaluators;

import dev.langchain4j.data.document.Document;

import java.util.List;
import java.util.Map;

public interface RetrievalEvaluator {

    public Map<String, Double> evaluate(List<Document> groundTruthDocuments, List<Document> retrievedDocuments);
}
