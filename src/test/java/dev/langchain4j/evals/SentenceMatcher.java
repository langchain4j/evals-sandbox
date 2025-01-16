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

        //I think this is really promising
        //It passes all the tests in the MatcherText class except the one where the retrieved sentences are cut mid-sentence
        //It is very effective but strict, in order for the groundTruth string to be a match, every sentence in it must be contained in the list of retrieved strings
        //Really this calculates recall in its current configuration since we are trying to match a ground truth string against a list of retrieved strings
        //But precision can be calculated by flipping the arguments of the method (providing A retrieved string and trying to match it against a list of ground truths)
        //If the criteria is too strict, we can add a threshold that the user has access to determine how many sentences should be found before we consider it a match

        Set<String> groundTruthSentences = Set.of(sentenceDetector.sentDetect(groundTruth));
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
