package dev.langchain4j.evals;

import java.util.List;

public interface Matcher {

    double match(List<String> groundTruths, List<String> allRetrieved);
}
