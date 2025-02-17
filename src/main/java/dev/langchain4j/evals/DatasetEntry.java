package dev.langchain4j.evals;

import dev.langchain4j.data.segment.TextSegment;

import java.util.List;
import java.util.Map;

record DatasetEntry(String query, List<TextSegment> expectedResults) {

}
