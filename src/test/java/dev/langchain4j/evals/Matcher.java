package dev.langchain4j.evals;

import java.util.List;

public interface Matcher {

    boolean match(String groundTruth, List<String> retrieved);
}
