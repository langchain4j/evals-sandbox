package dev.langchain4j.evals.documentRelevance;


import com.google.common.collect.Sets;
import dev.langchain4j.evals.customROUGE.utils.TokenizerUtils;
import opennlp.tools.tokenize.Tokenizer;

import java.util.*;
import java.util.stream.Collectors;

public class TokenMatching {
    public static void main(String[] args) {
        String word1 = "The quick brown fox jumps over the lazy dog.";
        String word2 = "The quick brown fox leaps over the lazy frog.";

        Tokenizer tokenizer = TokenizerUtils.getCommonsTokenizer();

        HashSet<String> gtStringTokens = new HashSet<>(Arrays.stream(tokenizer.tokenize(word1.toLowerCase())).toList());
        HashSet<String> retrievedStringTokens = new HashSet<>(Arrays.stream(tokenizer.tokenize(word2.toLowerCase())).toList());

        Set<String> commonTokens = Sets.intersection(gtStringTokens, retrievedStringTokens);

        System.out.println("Precision: " + calculateTokenPrecision(gtStringTokens, retrievedStringTokens, commonTokens));
        System.out.println("Recall: " + calculateTokenRecall(gtStringTokens, retrievedStringTokens, commonTokens));

        for (int i = 0; i < Samples.sampleGroundTruths.size(); i++) {
            System.out.println("Run: " + i);

            //Get documents for the scenario and replace any file paths with the content they point to
            List<Set<String>> groundTruths = Samples.sampleGroundTruths
                    .get(i)
                    .stream()
                    .map(Samples::fileToContent)
                    .map(String::toLowerCase)
                    .map(document -> Arrays.stream(tokenizer.tokenize(document)).collect(Collectors.toSet()))
                    .toList();
            List<Set<String>> retrievedDocuments = Samples.sampleRetrievedDocuments
                    .get(i)
                    .stream()
                    .map(Samples::fileToContent)
                    .map(String::toLowerCase)
                    .map(document -> Arrays.stream(tokenizer.tokenize(document)).collect(Collectors.toSet()))
                    .toList();

            for (Set<String> retrievedDocumentTokens : retrievedDocuments) {
                System.out.println("Testing Document:\n-------------------\n\n-------------------\n");
                double precision = 0.0;
                double recall = 0.0;
                for (Set<String> groundTruthDocumentTokens : groundTruths) {
                    double calculatedPrecision = calculateTokenPrecision(groundTruthDocumentTokens, retrievedDocumentTokens, Sets.intersection(groundTruthDocumentTokens, retrievedDocumentTokens));
                    if (calculatedPrecision > precision) {
                        precision = calculatedPrecision;
                    }
                    double calculatedRecall = calculateTokenRecall(groundTruthDocumentTokens, retrievedDocumentTokens, Sets.intersection(groundTruthDocumentTokens, retrievedDocumentTokens));
                    if (calculatedRecall > recall) {
                        recall = calculatedRecall;
                    }
                }
                System.out.println("Highest Precision: " + precision);
                System.out.println("Highest Recall: " + recall);
            }
        }
    }

    private static double calculateTokenPrecision(Set<String> groundTrouthTokens, Set<String> retrievedTokens, Set<String> commonTokens) {
        return 1.0 * commonTokens.size() / retrievedTokens.size();
    }

    private static double calculateTokenRecall(Set<String> groundTrouthTokens, Set<String> retrievedTokens, Set<String> commonTokens) {
        return 1.0 * commonTokens.size() / groundTrouthTokens.size();
    }
}
