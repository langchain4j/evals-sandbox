package dev.langchain4j.evals;

import me.xdrop.fuzzywuzzy.FuzzySearch;

import java.util.List;

public class FuzzyMatcher implements Matcher{

    @Override
    public boolean match(String groundTruth, List<String> retrieved) {
        double highestScore = 0;
        for (String retrievedString : retrieved){
             double score = FuzzySearch.weightedRatio(groundTruth, retrievedString);
             if (score > highestScore){
                 highestScore = score;
             }
        }
        System.out.println(highestScore);
        return highestScore > 86;
    }
}
