package dev.langchain4j.evals.logs;

import com.fasterxml.jackson.annotation.JsonProperty;

class JsonPayload {

    @JsonProperty("message")
    String message;

    @JsonProperty("@type")
    String type;
}