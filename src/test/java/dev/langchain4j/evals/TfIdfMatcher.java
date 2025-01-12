package dev.langchain4j.evals;

import dev.langchain4j.evals.documentRelevance.Tf_idf;

import java.util.*;

public class TfIdfMatcher implements Matcher{

    public static double calculateCosineSimilarity(Map<String, Double> vec1, Map<String, Double> vec2) {
        Set<String> intersection = new HashSet<>(vec1.keySet());
        intersection.retainAll(vec2.keySet());

        double numerator = 0.0;
        for (String word : intersection) {
            numerator += vec1.get(word) * vec2.get(word);
        }

        double sum1 = vec1.values().stream().mapToDouble(val -> val * val).sum();
        double sum2 = vec2.values().stream().mapToDouble(val -> val * val).sum();
        double denominator = Math.sqrt(sum1) * Math.sqrt(sum2);

        return denominator > 0 ? numerator / denominator : 0.0;
    }

    public static Map<String, Double> computeTF(String[] words) {
        Map<String, Double> tf = new HashMap<>();
        int totalWords = words.length;

        for (String word : words) {
            tf.put(word, tf.getOrDefault(word, 0.0) + 1.0);
        }

        for (Map.Entry<String, Double> entry : tf.entrySet()) {
            entry.setValue(entry.getValue() / totalWords);
        }

        return tf;
    }

    public static Map<String, Double> computeIDF(List<String[]> documents) {
        Map<String, Double> idf = new HashMap<>();
        int totalDocs = documents.size();

        for (String[] doc : documents) {
            Set<String> uniqueWords = new HashSet<>(Arrays.asList(doc));
            for (String word : uniqueWords) {
                idf.put(word, idf.getOrDefault(word, 0.0) + 1.0);
            }
        }

        for (Map.Entry<String, Double> entry : idf.entrySet()) {
            entry.setValue(Math.log(totalDocs / (entry.getValue() + 1)));
        }

        return idf;
    }

    public static Map<String, Double> computeTFIDF(Map<String, Double> tf, Map<String, Double> idf) {
        Map<String, Double> tfidf = new HashMap<>();

        for (String word : tf.keySet()) {
            tfidf.put(word, tf.get(word) * idf.getOrDefault(word, 0.0));
        }

        return tfidf;
    }

    @Override
    public boolean match(String groundTruth, List<String> retrieved) {

        //Tokenize everything
        String[] groudTruthstokenized = groundTruth.split("\\s+");
        List<String[]> retrievedDocumentstokenized = retrieved.stream().map(document -> document.split("\\s+")).toList();
        List<String[]> allTokens = new ArrayList<>(retrievedDocumentstokenized);
        allTokens.addAll(retrievedDocumentstokenized);

        //Compute the IDF for all the current documents
        Map<String, Double> idf = computeIDF(allTokens);

        //calculate the TFs
        Map<String, Double> groundTruthTF = computeTF(groudTruthstokenized);
        List<Map<String, Double>> retrievedDocumentTFs = retrievedDocumentstokenized.stream().map(Tf_idf::computeTF).toList();

        //calculate the final vectors for all the documents
        Map<String, Double> groundTruthTFIDF = computeTFIDF(groundTruthTF, idf);

        List<Map<String, Double>> retrievedDocumentTFIDFs = new ArrayList<>();
        for (Map<String, Double> retrievedDocumentTF : retrievedDocumentTFs){
            retrievedDocumentTFIDFs.add(computeTFIDF(retrievedDocumentTF, idf));
        }

        double similarity = 0.0;
        for (Map<String, Double> retrievedDocumentTFIDF : retrievedDocumentTFIDFs) {
            double calculatedSimilarity = calculateCosineSimilarity(retrievedDocumentTFIDF, groundTruthTFIDF);
            if (calculatedSimilarity > similarity) {
                similarity = calculatedSimilarity;
            }
        }

        return similarity >= 0.7;
    }
}
