package dev.langchain4j.evals.logs;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

class Resource {

    @JsonProperty("type")
    String type;

    @JsonProperty("labels")
    Map<String, String> labels;
}