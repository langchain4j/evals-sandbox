package dev.langchain4j.evals.documentRelevance;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Tf_idf {

    public static void main(String[] args) {
        for (int i = 0; i < Samples.sampleGroundTruths.size(); i++) {
                System.out.println("Run: " + i);

                //Get documents for the scenario and replace any file paths with the content they point to
                List<String> groundTruths = Samples.sampleGroundTruths.get(i).stream().map(Samples::fileToContent).map(String::toLowerCase).toList();
                List<String> retrievedDocuments = Samples.sampleRetrievedDocuments.get(i).stream().map(Samples::fileToContent).map(String::toLowerCase).toList();

                //Tokenize all documents
                List<String[]> groudTruthstokenized = groundTruths.stream().map(document -> document.split("\\s+")).toList();
                List<String[]> retrievedDocumentstokenized = retrievedDocuments.stream().map(document -> document.split("\\s+")).toList();
                List<String[]> allTokens = new ArrayList<>(groudTruthstokenized);
                allTokens.addAll(retrievedDocumentstokenized);

                //Compute the IDF for all the current documents
                Map<String, Double> idf = computeIDF(allTokens);

                //calculate the TFs
                List<Map<String, Double>> groundTruthTFs = groudTruthstokenized.stream().map(Tf_idf::computeTF).toList();
                List<Map<String, Double>> retrievedDocumentTFs = retrievedDocumentstokenized.stream().map(Tf_idf::computeTF).toList();

                //calculate the final vectors for all the documents
                List<Map<String, Double>> groundTruthTFIDFs = new ArrayList<>();
                for (Map<String, Double> groundTruthTF : groundTruthTFs){
                    groundTruthTFIDFs.add(computeTFIDF(groundTruthTF, idf));
                }
                List<Map<String, Double>> retrievedDocumentTFIDFs = new ArrayList<>();
                for (Map<String, Double> retrievedDocumentTF : retrievedDocumentTFs){
                    retrievedDocumentTFIDFs.add(computeTFIDF(retrievedDocumentTF, idf));
                }

                List<String> relevantDocuments = new ArrayList<>();

                for (int j = 0; j < retrievedDocuments.size(); j++){
                    double similarity = 0.0;
                    System.out.println("Testing Document:\n-------------------\n"+ retrievedDocuments.get(j)+"\n-------------------\n");
                    for (Map<String, Double> groundTruthTFIDF : groundTruthTFIDFs) {
                        double calculatedSimilarity = calculateCosineSimilarity(groundTruthTFIDF, retrievedDocumentTFIDFs.get(j));
                        if (calculatedSimilarity > similarity) {
                            similarity = calculatedSimilarity;
                        }
                    }
                    System.out.println("Highest Similarity: "+ similarity);
                    if (similarity >= 0.5) {
                        System.out.println("Document is deemed as relevant, adding");
                        relevantDocuments.add(retrievedDocuments.get(j));
                    }
                }
                System.out.println("Relevant documents:");
                for (String relevantDocument : relevantDocuments) {
                    System.out.println(relevantDocument);
                }
        }
    }

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
}
