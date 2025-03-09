package dev.langchain4j.evals.evaluators;

import dev.langchain4j.evals.customROUGE.ROUGE;

import java.util.Map;

public class AnswerSimilarityEvaluator {
    public Map<String, Double> evaluate(String groundTruthAnswer, String retrievedAnswer) {
        ROUGE rougeScorer = new ROUGE(ROUGE.RougeTypes.ROUGEL);
        double score = rougeScorer.calculate(groundTruthAnswer, retrievedAnswer);
        return Map.of("AnswerScore", score);
    }
}
