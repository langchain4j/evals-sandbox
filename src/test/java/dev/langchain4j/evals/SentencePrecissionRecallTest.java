package dev.langchain4j.evals;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SentencePrecissionRecallTest {

    @Test
    public void SentencePrecissionRecallTest() {
        List<String> groundTruthContents = Stream.of(
                """
                By implementing `StreamingResponseHandler`, you can define actions for the following events:
                - When the next token is generated: `onNext(String token)` is invoked.
                For instance, you can send the token directly to the UI as soon as it becomes available.
                - When the LLM has completed generation: `onComplete(Response<T> response)` is invoked.
                Here, `T` stands for `AiMessage` in the case of `StreamingChatLanguageModel`,
                and `String` for `StreamingLanguageModel`. The `Response` object contains the complete response.
                - When an error occurs: `onError(Throwable error)` is invoked.
                """,
                """
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
                """
        ).toList();

        List<String> referenceContents = Stream.of(
                """
                By implementing `StreamingResponseHandler`, you can define actions for the following events:
                - When the next token is generated: `onNext(String token)` is invoked.
                For instance, you can send the token directly to the UI as soon as it becomes available.
                """,
                """
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
                """,
                """
                Interestingly, for debugging, tweaking or even just knowing all the available parameters,
                one can have a look in the quarkus DEV UI.
                In this dashboard, you can make changes that will be immediately reflected in your running instance,
                and your changes are automatically ported to the code.
                The DEV UI can be accessed by running your Quarkus application with the command `quarkus dev`,
                then you can find it on localhost:8080/q/dev-ui (or wherever you deploy your application).
                """
        ).toList();

        SentenceDetectorME sentenceDetector = null;

        try (InputStream modelfile = this.getClass().getResourceAsStream("/opennlp-en-ud-ewt-sentence-1.0-1.9.3.bin")){
            assert modelfile != null;
            SentenceModel model = new SentenceModel(modelfile);
            sentenceDetector = new SentenceDetectorME(model);
        } catch (IOException e){
            System.out.println("The file was not found");
        }

        String[] sentencesFromString1 = sentenceDetector.sentDetect(String.join("\n", groundTruthContents));
        String[] sentencesFromString2 = sentenceDetector.sentDetect(String.join("\n", referenceContents));

        List<String> commonSentences = new ArrayList<>();

        for (String sentence : sentencesFromString1) {
            for (String sentence2 : sentencesFromString2) {
                if (sentence.equals(sentence2)) {
                    commonSentences.add(sentence);
                }
            }
        }

        System.out.println(sentencesFromString1.length);

        double precission = 1 * (commonSentences.size() / (double) (sentencesFromString1.length));
        double recall =  1 * (commonSentences.size() / (double) (sentencesFromString2.length));

        System.out.println("Precission: " + precission);
        System.out.println("Recall: " + recall);

        System.out.println("done");

    }
}
