package dev.langchain4j.evals;

import java.util.List;

public class OpenAiEmbedingsMatcher implements Matcher {


    @Override
    public boolean match(String groundTruth, List<String> retrieved) {
        return false;
    }
}
