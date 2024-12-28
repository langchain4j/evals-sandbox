package dev.langchain4j.evals;

import java.util.List;

public class ExactMatcher implements Matcher {

    @Override
    public double match(List<String> groundTruths, List<String> allRetrieved) {

        int matched = 0;

        for (String groundTruth : groundTruths) {
            if (allRetrieved.contains(groundTruth)) {
                matched++;
            }
        }

        return (double) matched / groundTruths.size();
    }
}
