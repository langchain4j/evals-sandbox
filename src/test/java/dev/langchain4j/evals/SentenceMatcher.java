package dev.langchain4j.evals;



import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

import java.io.*;
import java.util.List;
import java.util.Set;

public class SentenceMatcher implements Matcher{

    private static SentenceDetectorME sentenceDetector;

    public SentenceMatcher() {

        //Used the english model for testing for now but we can add a languadge detector for other languages and the logic below stays the same.
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
//        for (Set<String> retrievedDocument : retrievedDocumentSentences) {
//            if (groundTruthSentences.containsAll(retrievedDocument)){
//                return true;
//            }
//        }

//        or
        for (String sentence : groundTruthSentences){
            boolean match = false;
            for (String retrievedText : retrieved){
                if (retrievedText.contains(sentence)){
                    match = true;
                    break;
                }
            }
            if (!match) return false;
        }
        return true;
    }
}
