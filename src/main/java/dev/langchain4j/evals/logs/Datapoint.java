package dev.langchain4j.evals.logs;

import java.util.ArrayList;
import java.util.List;

class Datapoint {

    String sessionId;
    String queryId;
    String query;
    List<String> retrieved = new ArrayList<>();
    String answer;
    List<Boolean> liked = new ArrayList<>();
    String timestamp;
}
