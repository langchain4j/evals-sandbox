package dev.langchain4j.evals.logs;

import java.io.File;
import java.io.IOException;
import java.util.*;

class LogParser {

    public static void main(String[] args) {

        File folder = new File("C:\\Users\\ljuba\\Desktop\\docu_chat_logs");

        try {
            List<LogEntry> logEntries = JsonFileReader.readLogEntriesFromFolder(folder);

            // Filter out duplicates while maintaining order
            Map<String, LogEntry> uniqueEntries = new LinkedHashMap<>();

            for (LogEntry entry : logEntries) {
                uniqueEntries.putIfAbsent(entry.insertId, entry);
            }

            Map<String, Datapoint> datapoints = new HashMap<>();

            // Process the unique entries as needed
            for (LogEntry entry : uniqueEntries.values()) {

                String logMessage = null;
                if (entry.jsonPayload != null) {
                    logMessage = entry.jsonPayload.message;
                } else {
                    logMessage = entry.textPayload.substring((entry.textPayload.split(" - ")[0].length() + 3));
                }

                if (logMessage.contains(";;;question;;;")) {
                    String[] split = logMessage.split(";;;");

                    String sessionId;
                    String queryId;
                    String query;

                    if (split.length == 3) {
                        sessionId = null;
                        queryId = split[0];
                        query = split[2];
                    } else if (split.length == 4) {
                        sessionId = split[0];
                        queryId = split[1];
                        query = split[3];
                    } else {
                        throw new IllegalArgumentException("sddd");
                    }

                    Datapoint datapoint = datapoints.computeIfAbsent(queryId, ignored -> new Datapoint());
                    datapoint.sessionId = sessionId;
                    datapoint.queryId = queryId;
                    datapoint.query = query;
                    datapoint.timestamp = entry.timestamp;

                } else if (logMessage.contains(";;;answer;;;")) {
                    String[] split = logMessage.split(";;;");

                    String sessionId;
                    String queryId;
                    String answer;

                    if (split.length == 3) {
                        sessionId = null;
                        queryId = split[0];
                        answer = split[2];
                    } else if (split.length == 4) {
                        sessionId = split[0];
                        queryId = split[1];
                        answer = split[3];
                    } else {
                        throw new IllegalArgumentException("sdsgf");
                    }

                    Datapoint datapoint = datapoints.computeIfAbsent(queryId, ignored -> new Datapoint());
                    if (!Objects.equals(datapoint.sessionId, sessionId)) {
                        throw new IllegalArgumentException("dsghtfgfg");
                    }
                    if (!datapoint.queryId.equals(queryId)) {
                        throw new IllegalArgumentException("fdgedffas");
                    }
                    datapoint.answer = answer;

                } else if (logMessage.contains(";;;liked;;;")) {
                    String[] split = logMessage.split(";;;");

                    String sessionId;
                    String queryId;
                    String liked;

                    if (split.length == 3) {
                        sessionId = null;
                        queryId = split[0];
                        liked = split[2];
                    } else if (split.length == 4) {
                        sessionId = split[0];
                        queryId = split[1];
                        liked = split[3];
                    } else {
                        throw new IllegalArgumentException("ddfswwd");
                    }

                    Datapoint datapoint = datapoints.computeIfAbsent(queryId, ignored -> new Datapoint());
                    if (!Objects.equals(datapoint.sessionId, sessionId)) {
                        throw new IllegalArgumentException("trtreqwwqs");
                    }
                    if (!datapoint.queryId.equals(queryId)) {
                        throw new IllegalArgumentException("6uiuzhger");
                    }
                    datapoint.liked.add(Boolean.parseBoolean(liked));
                }
            }

            List<String> results = datapoints.values().stream()
                    .sorted(Comparator.comparing(it -> it.timestamp))
                    .map(it -> it.timestamp + " -> " + it.query)
                    .toList();

            System.out.println(String.join("\n", results));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
