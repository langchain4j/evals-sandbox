package dev.langchain4j.evals;

import java.util.List;

public class ContainsMatcher implements Matcher {

    @Override
    public double match(List<String> groundTruths, List<String> allRetrieved) {

        int matched = 0;

        for (String groundTruth : groundTruths) {
            inner:
            for (String retrieved : allRetrieved) {
                if (retrieved.contains(groundTruth)) {
                    matched++;
                    break inner;
                }
            }
        }

        return (double) matched / groundTruths.size();
    }
}
