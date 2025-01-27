package dev.langchain4j.evals.evaluators;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.segment.TextSegment;
import me.xdrop.fuzzywuzzy.FuzzySearch;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FuzzyMatchingChunkEvaluator implements RetrievalEvaluator{

    private boolean match(String string1, String string2){
        return FuzzySearch.weightedRatio(string1, string2) > 86.5;
    }

    @Override
    public Map<String, Double> evaluate(List<TextSegment> groundTruthDocuments, List<TextSegment> retrievedDocuments) {
        List<String> groundTruthChunks = groundTruthDocuments.stream().map(TextSegment::text).toList();
        List<String> retrievedChunks = retrievedDocuments.stream().map(TextSegment::text).toList();


        Set<String> hitGroundTruthChunks = new HashSet<>();
        int relevantRetrievedChunks = 0;

        for (String retrievedChunk : retrievedChunks) {
            boolean retrievedChunkFound = false;
            for (String groundTruthChunk : groundTruthChunks) {
                if (match(retrievedChunk, groundTruthChunk)) {
                    retrievedChunkFound = true;
                    hitGroundTruthChunks.add(retrievedChunk);
                    continue;
                }
                if (retrievedChunkFound) {
                    relevantRetrievedChunks++;
                }
            }
        }
        double precision = 1 * (relevantRetrievedChunks / (double) (retrievedChunks.size()));
        double recall = 1 * (hitGroundTruthChunks.size() / (double) (groundTruthChunks.size()));

        return Map.of("Precision", precision, "Recall", recall);
    }
}
