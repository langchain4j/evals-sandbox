package dev.langchain4j.evals;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.Metadata;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.evals.evaluators.FuzzyMatchingChunkEvaluator;
import dev.langchain4j.evals.evaluators.RougeMatchingChunkEvaluator;
import dev.langchain4j.evals.evaluators.SentenceMatchingEvaluator;
import dev.langchain4j.evals.evaluators.TokenMatchingEvaluator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class PrecisionRecallTest {

    List<TextSegment> groundTruthContents = Stream.of(
            new TextSegment("""
                By implementing `StreamingResponseHandler`, you can define actions for the following events:
                """, new Metadata()),
            new TextSegment("""
                A more compact way to stream the response is to use the `LambdaStreamingResponseHandler` class.
                This utility class provides static methods to create a `StreamingResponseHandler` using lambda expressions.
                The way to use lambdas to stream the response is quite simple.
                ```java
                import static dev.langchain4j.model.LambdaStreamingResponseHandler.onNext;

                model.generate("Tell me a joke", onNext(System.out::print));
                ```

                The `onNextAndError()` method allows you to define actions for both the `onNext()` and `onError()` events:

                ```java
                import static dev.langchain4j.model.LambdaStreamingResponseHandler.onNextAndError;

                model.generate("Tell me a joke", onNextAndError(System.out::print, Throwable::printStackTrace));
                """, new Metadata())
    ).toList();

    List<TextSegment> referenceContents = Stream.of(
            new TextSegment("""
                By implementing `StreamingResponseHandler`, you can define actions for the following events:
                - When the next token is generated: `onNext(String token)` is invoked.
                For instance, you can send the token directly to the UI as soon as it becomes available.
                - When the LLM has completed generation: `onComplete(Response<T> response)` is invoked.
                Here, `T` stands for `AiMessage` in the case of `StreamingChatLanguageModel`,
                and `String` for `StreamingLanguageModel`. The `Response` object contains the complete response.
                - When an error occurs: `onError(Throwable error)` is invoked.
                """, new Metadata()),
            new TextSegment("""
                A more compact way to stream the response is to use the `LambdaStreamingResponseHandler` class.
                This utility class provides static methods to create a `StreamingResponseHandler` using lambda expressions.
                The way to use lambdas to stream the response is quite simple.
                ```java
                import static dev.langchain4j.model.LambdaStreamingResponseHandler.onNext;

                model.generate("Tell me a joke", onNext(System.out::print));
                ```

                The `onNextAndError()` method allows you to define actions for both the `onNext()` and `onError()` events:

                ```java
                import static dev.langchain4j.model.LambdaStreamingResponseHandler.onNextAndError;

                model.generate("Tell me a joke", onNextAndError(System.out::print, Throwable::printStackTrace));
                """, new Metadata()),
            new TextSegment("""
                Interestingly, for debugging, tweaking or even just knowing all the available parameters,
                one can have a look in the quarkus DEV UI.
                In this dashboard, you can make changes that will be immediately reflected in your running instance,
                and your changes are automatically ported to the code.
                The DEV UI can be accessed by running your Quarkus application with the command `quarkus dev`,
                then you can find it on localhost:8080/q/dev-ui (or wherever you deploy your application).
                """, new Metadata())
    ).toList();

    @Test
    public void SentencePrecisionRecallTest() {

        SentenceMatchingEvaluator evaluator = new SentenceMatchingEvaluator();

        Map<String,Double> results = evaluator.evaluate(groundTruthContents, referenceContents);

        System.out.println("Precission: " + results.get("Precision"));
        System.out.println("Recall: " + results.get("Recall"));
    }

    @Test
    public void TokenMatchingTest() {

        TokenMatchingEvaluator evaluator = new TokenMatchingEvaluator();

        Map<String,Double> results = evaluator.evaluate(groundTruthContents, referenceContents);

        System.out.println("Precission: " + results.get("Precision"));
        System.out.println("Recall: " + results.get("Recall"));
    }

    @Test
    public void FuzzyMatchingTest() {

        FuzzyMatchingChunkEvaluator evaluator = new FuzzyMatchingChunkEvaluator();

        Map<String,Double> results = evaluator.evaluate(groundTruthContents, referenceContents);

        System.out.println("Precission: " + results.get("Precision"));
        System.out.println("Recall: " + results.get("Recall"));
    }

    @Test
    public void RougeMatchingTest() {

        RougeMatchingChunkEvaluator evaluator = new RougeMatchingChunkEvaluator();

        Map<String,Double> results = evaluator.evaluate(groundTruthContents, referenceContents);

        System.out.println("Recall: " + results.get("Recall"));
    }

}
