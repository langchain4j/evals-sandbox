package dev.langchain4j.evals;

import dev.langchain4j.data.document.Metadata;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.evals.documentRelevance.Rouge;
import dev.langchain4j.evals.evaluators.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvaluatorTest {
    public static void main(String[] args) {
        for (RetrievalEvaluator evaluator : evaluators()){
            System.out.println("================================================================================");
            System.out.println(evaluator.getClass());
            System.out.println("================================================================================");

            test(
                    evaluator,
                    toTextSegmentList(List.of("I like apples.")),
                    toTextSegmentList(List.of("I like apples.")),
                    Map.of("Precision", 1.0, "Recall", 1.0)
            );

            test(
                    evaluator,
                    toTextSegmentList(List.of("I like apples.")),
                    toTextSegmentList(List.of("I like bananas.")),
                    Map.of("Precision", 0.0, "Recall", 0.0)
            );

            test(
                    evaluator,
                    toTextSegmentList(List.of("I like apples.")),
                    toTextSegmentList(List.of()),
                    Map.of("Precision", 0.0, "Recall", 0.0)
            );

            test(
                    evaluator,
                    toTextSegmentList(List.of("I like apples.")),
                    toTextSegmentList(List.of("I like apples.", "I like bananas.")),
                    Map.of("Precision", 0.5, "Recall", 1.0)
            );

            test(
                    evaluator,
                    toTextSegmentList(List.of("I like apples.")),
                    toTextSegmentList(List.of("I like bananas.", "I like apples.")),
                    Map.of("Precision", 0.5, "Recall", 1.0)
            );

            test(
                    evaluator,
                    toTextSegmentList(List.of("I like apples.")),
                    toTextSegmentList(List.of("I like apples.", "I like apples.")),
                    Map.of("Precision", 1.0, "Recall", 1.0)
            );

            test(
                    evaluator,
                    toTextSegmentList(List.of("I like apples.")),
                    toTextSegmentList(List.of("I like cherries.", "I like bananas.")),
                    Map.of("Precision", 0.0, "Recall", 0.0)
            );

            test(
                    evaluator,
                    toTextSegmentList(List.of("I like apples.", "I like bananas.")),
                    toTextSegmentList(List.of("I like apples.", "I like b.", "I like c.", "I like d.", "I like e.", "I like f.")),
                    Map.of("Recall", 0.5, "Precision", 0.16)
            );

            test(
                    evaluator,
                    toTextSegmentList(List.of("I like apples.")),
                    toTextSegmentList(List.of("I like apples. I like b. I like c. I like d. I like e. I like f. I like g.")),
                    Map.of("Recall", 1.0, "Precision", 0.16)
            );

            test(
                    evaluator,
                    toTextSegmentList(List.of("I like apples.")),
                    toTextSegmentList(List.of("I like b.", "I like c.", "I like d.", "I like e.", "I like f.", "I like apples.")),
                    Map.of("Recall", 1.0, "Precision", 0.166666)
            );

            test(
                    evaluator,
                    toTextSegmentList(List.of("I like apples.")),
                    toTextSegmentList(List.of("I like b. I like c. I like d. I like e. I like f. I like g. I like apples.")),
                    Map.of("Recall", 1.0, "Precision", 0.16666666666666666)
            );

            // ground truth split across multiple retrieved (due to chunking)

            test(
                    evaluator,
                    toTextSegmentList(List.of("I like apples. I like bananas.")),
                    toTextSegmentList(List.of("I like apples.", "I like bananas.")),
                    // TODO count is as "match" only if both retrieved come from the same document and are consecutive?
                    Map.of("Recall", 1.0, "Precision", 1.0)
            );

            test(
                    evaluator,
                    toTextSegmentList(List.of("\nI like apples.\n\nI like bananas.\n")), // TODO test more cases like this
                    // TODO count is as "match" only if both retrieved come from the same document and are consecutive?
                    toTextSegmentList(List.of("I like apples.", "I like bananas.")),
                    Map.of("Recall", 1.0, "Precision", 1.0)
            );

            test(
                    evaluator,
                    toTextSegmentList(List.of("I like apples. I like bananas.")),
                    // TODO count is as "match" only if both retrieved come from the same document and are consecutive?
                    toTextSegmentList(List.of("I like\napples.", "I like\nbananas.")),
                    Map.of("Recall", 1.0, "Precision", 1.0)
            );

            test(
                    evaluator,
                    toTextSegmentList(List.of("I like apples. I like bananas.")),
                    // TODO count is as "match" only if both retrieved come from the same document and are consecutive?
                    toTextSegmentList(List.of("I like bananas.", "I like apples.")),
                    Map.of("Recall", 1.0, "Precision", 1.0)
            );

            test(
                    evaluator,
                    toTextSegmentList(List.of("I like apples. I like bananas.")),
                    toTextSegmentList(List.of("I like apples. I like bananas.", "I like apples. I like bananas.")),
                    Map.of("Recall", 1.0, "Precision", 1.0)
            );

            test(
                    evaluator,
                    toTextSegmentList(List.of("I like apples. I like bananas.")),
                    // TODO count is as "match" only if both retrieved come from the same document and are consecutive?
                    toTextSegmentList(List.of("I like apples.", "I like bananas.", "I like apples.", "I like bananas.")),
                    Map.of("Recall", 1.0, "Precision", 1.0)
            );

            test(
                    evaluator,
                    toTextSegmentList(List.of("I like apples. I like bananas.")),
                    // TODO count is as "match" only if both retrieved come from the same document and are consecutive?
                    toTextSegmentList(List.of("I like apples.", "I like bananas.", "I like cherries.")),
                    Map.of("Recall", 1.0, "Precision", 0.5)
            );

            test(
                    evaluator,
                    toTextSegmentList(List.of("I like apples. I like bananas.")),
                    toTextSegmentList(List.of("I like apples.", "I like cherries.")),
                    Map.of("Recall", 1.0, "Precision", 0.5)
            );

            test(
                    evaluator,
                    toTextSegmentList(List.of("I like apples. I like bananas.")),
                    toTextSegmentList(List.of("I like bananas.", "I like cherries.", "I like apples.")),
                    Map.of("Recall", 1.0, "Precision", 0.6) // TODO count is as "match" only if both retrieved come from the same document and are consecutive?
            );


            // potential false positives

            test(
                    evaluator,
                    toTextSegmentList(List.of("I like apples.")),
                    toTextSegmentList(List.of("I like pineapples.")),
                    Map.of("Recall", 0.0, "Precision", 0.0)
            );

            test(
                    evaluator,
                    toTextSegmentList(List.of("I like apples.")),
                    toTextSegmentList(List.of("I do not like apples.")),
                    Map.of("Recall", 0.0, "Precision", 0.0)
            );

            test(
                    evaluator,
                    toTextSegmentList(List.of("I like apples.")),
                    toTextSegmentList(List.of("I like bananas.", "He likes apples.")),
                    Map.of("Recall", 0.0, "Precision", 0.0)
            );
        }
    }

    private static void test(RetrievalEvaluator evaluator, List<TextSegment> groundTruth, List<TextSegment> retrieved, Map<String,Double> expected) {
        Map<String, Double> results = evaluator.evaluate(groundTruth, retrieved);
        for (String key : expected.keySet()) {
            if (!expected.get(key).equals(results.get(key))) {
                System.out.println("Expected" + expected);
                System.out.println("Actual" + results);
                break;
            }
        }
    }

    private static List<TextSegment> toTextSegmentList(List<String> ListOfStrings){
        List<TextSegment> textSegments = new ArrayList<>();
        for (String string : ListOfStrings) {
            textSegments.add(new TextSegment(string, new Metadata()));
        }
        return textSegments;
    }

    static List<RetrievalEvaluator> evaluators(){
        return List.of(
                new FuzzyMatchingChunkEvaluator(),
                new SentenceMatchingEvaluator(),
                new TokenMatchingEvaluator(),
                new RougeMatchingChunkEvaluator()
        );
    }
}
