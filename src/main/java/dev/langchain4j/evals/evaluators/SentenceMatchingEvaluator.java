package dev.langchain4j.evals.evaluators;

import dev.langchain4j.data.document.Document;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SentenceMatchingEvaluator implements RetrievalEvaluator {

    private static SentenceDetectorME sentenceDetector;

    public SentenceMatchingEvaluator() {
        try (InputStream modelfile = this.getClass().getResourceAsStream("/opennlp-en-ud-ewt-sentence-1.0-1.9.3.bin")){
            assert modelfile != null;
            SentenceModel model = new SentenceModel(modelfile);
            sentenceDetector = new SentenceDetectorME(model);
        } catch (IOException e){
            System.out.println("The file was not found");
        }
    }

    @Override
    public Map<String, Double> evaluate(List<Document> groundTruthDocuments, List<Document> retrievedDocuments) {

        List<String> groundTruthSentences = new ArrayList<>();
        List<String> retrievedSentences = new ArrayList<>();

        assert sentenceDetector != null;
        for (Document document : groundTruthDocuments){
            groundTruthSentences.addAll(List.of(sentenceDetector.sentDetect(document.text())));
        }

        for (Document retrievedDocument : retrievedDocuments){
            retrievedSentences.addAll(List.of(sentenceDetector.sentDetect(retrievedDocument.text())));
        }

        List<String> commonSentences = new ArrayList<>();

        for (String groundTruthSentence : groundTruthSentences) {
            for (String retrievedSentence : retrievedSentences) {
                if (groundTruthSentence.equals(retrievedSentence)) {
                    commonSentences.add(groundTruthSentence);
                }
            }
        }

        double precision = 1 * (commonSentences.size() / (double) (retrievedSentences.size()));
        double recall =  1 * (commonSentences.size() / (double) (groundTruthSentences.size()));

        return Map.of("Precision", precision, "Recall", recall);
    }
}
