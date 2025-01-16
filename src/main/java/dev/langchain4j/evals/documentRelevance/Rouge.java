package dev.langchain4j.evals.documentRelevance;




import dev.langchain4j.evals.customROUGE.ROUGE;

import java.util.ArrayList;
import java.util.List;

public class Rouge {
    public static void main(String[] args) {
        ROUGE RougeScorer = new ROUGE(ROUGE.RougeTypes.ROUGEL);

        for (int i = 0; i < Samples.sampleGroundTruths.size(); i++) {
            System.out.println("Run: " + i);

            //Get documents for the scenario and replace any file paths with the content they point to
            List<String> groundTruths = Samples.sampleGroundTruths.get(i).stream().map(Samples::fileToContent).map(String::toLowerCase).toList();
            List<String> retrievedDocuments = Samples.sampleRetrievedDocuments.get(i).stream().map(Samples::fileToContent).map(String::toLowerCase).toList();


            List<String> relevantDocuments = new ArrayList<>();

            for (String retrievedDocument : retrievedDocuments) {
                double similarity = 0.0;
                System.out.println("Testing Document:\n-------------------\n\n-------------------\n");
                for (String groundTruthDocument : groundTruths) {
                    double calculatedSimilarity = RougeScorer.calculate(groundTruthDocument, retrievedDocument);
                    System.out.println("Calculated Similarity: " + calculatedSimilarity);
                    if (calculatedSimilarity > similarity) {
                        similarity = calculatedSimilarity;
                    }
                }
                System.out.println("Highest Similarity: " + similarity);

                //Similarity drops significantly when comparing big chunks of texts against small parts of them
                //But the difference between relevant and not relevant can still be seen
                if (similarity >= 0.09) {
                    System.out.println("Document is deemed as relevant, adding");
                    relevantDocuments.add(retrievedDocument);
                }
            }
            System.out.println("Relevant documents:" + relevantDocuments.size());
            System.out.println("All retrieved:" + retrievedDocuments.size());
        }
    }
}
