package dev.langchain4j.evals;

import java.util.List;

public class BagOfSentencesMatcher implements Matcher {

    @Override
    public double match(List<String> groundTruths, List<String> allRetrieved) {

        int matched = 0;

        for (String groundTruth : groundTruths) {
            for (String groundTruthSentence : groundTruth.split("\\.")) {
                inner:
                for (String retrieved : allRetrieved) {
                    if (retrieved.contains(groundTruthSentence)) {
                        matched++;
                        break inner;
                    }
                }
            }

        }

        return (double) matched / groundTruths.size();
    }
}
