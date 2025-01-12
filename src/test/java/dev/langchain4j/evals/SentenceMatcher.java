package dev.langchain4j.evals;



import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

import java.io.*;
import java.util.List;
import java.util.Set;

public class SentenceMatcher implements Matcher{

    private static SentenceDetectorME sentenceDetector;

    public SentenceMatcher() {
        try (InputStream modelfile = this.getClass().getResourceAsStream("/opennlp-en-ud-ewt-sentence-1.0-1.9.3.bin")){
            assert modelfile != null;
            SentenceModel model = new SentenceModel(modelfile);
            sentenceDetector = new SentenceDetectorME(model);
        } catch (IOException e){
            System.out.println("The file was not found");
        }
    }

    @Override
    public boolean match(String groundTruth, List<String> retrieved) {
        Set<String> groundTruthSentences = Set.of(sentenceDetector.sentDetect(groundTruth));
        List<Set<String>> retrievedSentences = retrieved.stream().map(sentenceDetector::sentDetect).map(Set::of).toList();
        for (Set<String> retrievedSentence : retrievedSentences) {
            if (retrievedSentence.contains(groundTruthSentences)) {
                return true;
            }
        }
        return false;
    }
}
