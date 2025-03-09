package dev.langchain4j.evals;

import dev.langchain4j.data.document.Metadata;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.evals.evaluators.RougeMatchingChunkEvaluator;
import dev.langchain4j.evals.evaluators.TokenMatchingEvaluator;

import java.util.List;

//Tried to do a performance test with python.
//I used continuous-eval since it allowed to just run the evaluation in order to just monitor this
//And it was also using rouge to calculate Precision and recall
//Hot times are similar both are in the range of 70-50 ms when only the calculation of the evaluation is measured.
//But when cold startup is considered (using the time command on linux and running the method and the python script)
//Python lags significantly behind.
public class PerformanceTest {
    public static void main(String[] args) {

        final long startTime = System.currentTimeMillis();

        RougeMatchingChunkEvaluator evaluator = new RougeMatchingChunkEvaluator();

        var results = evaluator.evaluate(
                List.of(TextSegment.from("Paris is the capital of France.", new Metadata())),
                List.of(
                TextSegment.from("Paris is the capital of France and its largest city.", new Metadata()),
                TextSegment.from("Lyon is a major city in France.", new Metadata())
                ));

        System.out.println(results);

        final long endTime = System.currentTimeMillis();

        System.out.println("Total execution time: " + (endTime - startTime) + " milliseconds");
    }
}
