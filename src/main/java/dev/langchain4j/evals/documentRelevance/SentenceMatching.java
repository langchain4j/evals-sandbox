package dev.langchain4j.evals.documentRelevance;

import com.google.common.collect.Sets;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import org.kie.trustyai.metrics.language.utils.tokenizers.TokenizerUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SentenceMatching {
    public static void main(String[] args) {

        SentenceDetectorME sentenceDetector = null;

        try (InputStream modelfile = SentenceMatching.class.getResourceAsStream("/opennlp-en-ud-ewt-sentence-1.0-1.9.3.bin")){
            assert modelfile != null;
            SentenceModel model = new SentenceModel(modelfile);
            sentenceDetector = new SentenceDetectorME(model);
        } catch (IOException e){
            System.out.println("The file was not found");
        }

        String word1 = "The quick brown fox jumps over the lazy dog.";
        String word2 = "The quick brown fox leaps over the lazy frog.";

        assert sentenceDetector != null;
        String[] groundTruth = sentenceDetector.sentDetect(word1);
        String[] retrievedDocumentSentences = sentenceDetector.sentDetect(word2);
        System.out.println(Arrays.toString(groundTruth));

        for (int i = 0; i < Samples.sampleGroundTruths.size(); i++) {
            System.out.println("Run: " + i);

            //Get documents for the scenario and replace any file paths with the content they point to
            SentenceDetectorME finalSentenceDetector = sentenceDetector;
            List<Set<String>> groundTruths = Samples.sampleGroundTruths
                    .get(i)
                    .stream()
                    .map(Samples::fileToContent)
                    .map(String::toLowerCase)
                    .map(document -> Arrays.stream(finalSentenceDetector.sentDetect(document)).collect(Collectors.toSet()))
                    .toList();
            List<Set<String>> retrievedDocuments = Samples.sampleRetrievedDocuments
                    .get(i)
                    .stream()
                    .map(Samples::fileToContent)
                    .map(String::toLowerCase)
                    .map(document -> Arrays.stream(finalSentenceDetector.sentDetect(document)).collect(Collectors.toSet()))
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
