package dev.langchain4j.evals.logs;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

class LogEntry {

    @JsonProperty("insertId")
    String insertId;

    @JsonProperty("jsonPayload")
    JsonPayload jsonPayload;

    @JsonProperty("textPayload")
    String textPayload;

    @JsonProperty("resource")
    Resource resource;

    @JsonProperty("timestamp")
    String timestamp;

    @JsonProperty("severity")
    String severity;

    @JsonProperty("labels")
    Map<String, String> labels;

    @JsonProperty("logName")
    String logName;

    @JsonProperty("receiveTimestamp")
    String receiveTimestamp;

    @JsonProperty("errorGroups")
    Object[] errorGroups;

    @Override
    public String toString() {
        return jsonPayload.message;
    }
}

