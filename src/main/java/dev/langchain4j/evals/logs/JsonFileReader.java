package dev.langchain4j.evals.logs;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class JsonFileReader {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static List<LogEntry> readLogEntriesFromFolder(File folder) throws IOException {
        List<LogEntry> logEntries = new ArrayList<>();

        if (folder.isDirectory()) {
            File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));

            if (files != null) {
                for (File file : files) {
                    System.out.println(file.getAbsolutePath());
                    LogEntry[] entries = objectMapper.readValue(file, LogEntry[].class);
                    for (LogEntry entry : entries) {
                        logEntries.add(entry);
                    }
                }
            }
        }

        return logEntries;
    }
}
