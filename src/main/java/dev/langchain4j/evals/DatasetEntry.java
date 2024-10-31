package dev.langchain4j.evals;

import java.util.Map;

record DatasetEntry(String query, Map<String, Double> expectedDocumentPaths) {

}
