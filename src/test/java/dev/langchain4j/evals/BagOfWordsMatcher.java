package dev.langchain4j.evals;

import java.util.List;

public class BagOfWordsMatcher implements Matcher {

    @Override
    public double match(List<String> groundTruths, List<String> allRetrieved) {

        int matched = 0;

        for (String groundTruth : groundTruths) {
            for (String groundTruthWord : groundTruth.split(" ")) {
                inner:
                for (String retrieved : allRetrieved) {
                    if (retrieved.contains(groundTruthWord)) {
                        matched++;
                        break inner;
                    }
                }
            }

        }

        return (double) matched / groundTruths.size();
    }
}
