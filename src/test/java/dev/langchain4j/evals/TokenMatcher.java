package dev.langchain4j.evals;

import com.google.common.collect.Sets;
import org.kie.trustyai.metrics.language.utils.tokenizers.TokenizerUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TokenMatcher implements Matcher {

    private static final opennlp.tools.tokenize.Tokenizer tokenizer = TokenizerUtils.getCommonsTokenizer();

    private static double calculateTokenPrecision(Set<String> groundTrouthTokens, Set<String> retrievedTokens, Set<String> commonTokens) {
        return 1.0 * commonTokens.size() / retrievedTokens.size();
    }

    private static double calculateTokenRecall(Set<String> groundTrouthTokens, Set<String> retrievedTokens, Set<String> commonTokens) {
        return 1.0 * commonTokens.size() / groundTrouthTokens.size();
    }

    @Override
    public boolean match(String groundTruth, List<String> retrieved) {

        //Get documents for the scenario and replace any file paths with the content they point to
        Set<String> groundTruthTokens = Arrays.stream(tokenizer.tokenize(groundTruth.toLowerCase())).collect(Collectors.toSet());
        List<Set<String>> retrievedTokens = retrieved
                .stream()
                .map(String::toLowerCase)
                .map(document -> Arrays.stream(tokenizer.tokenize(document)).collect(Collectors.toSet()))
                .toList();

        double precision = 0.0;
        double recall = 0.0;
        for (Set<String> retrievedDocumentTokens : retrievedTokens) {
            for (String groundTruthToken : groundTruthTokens) {
                double calculatedPrecision = calculateTokenPrecision(groundTruthTokens, retrievedDocumentTokens, Sets.intersection(groundTruthTokens, retrievedDocumentTokens));
                if (calculatedPrecision > precision) {
                    precision = calculatedPrecision;
                }
                double calculatedRecall = calculateTokenRecall(groundTruthTokens, retrievedDocumentTokens, Sets.intersection(groundTruthTokens, retrievedDocumentTokens));
                if (calculatedRecall > recall) {
                    recall = calculatedRecall;
                }
            }
        }

        return precision >= 0.6 && recall >= 0.6;
    }
}
