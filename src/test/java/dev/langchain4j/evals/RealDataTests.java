package dev.langchain4j.evals;

import org.junit.jupiter.api.Test;

import java.util.List;

public class RealDataTests {

    @Test
    public void happyPath() {
//      Match Chunks of Text against chunks of text, this is the most expected use case of the evaluator
        List<String> groundTurthChunks = List.of(
                """
                By implementing `StreamingResponseHandler`, you can define actions for the following events:
                - When the next token is generated: `onNext(String token)` is invoked.
                For instance, you can send the token directly to the UI as soon as it becomes available.
                """
        );
        List<String> retrievedChunks = List.of(
                """
                By implementing `StreamingResponseHandler`, you can define actions for the following events:
                - When the next token is generated: `onNext(String token)` is invoked.
                For instance, you can send the token directly to the UI as soon as it becomes available.
                - When the LLM has completed generation: `onComplete(Response<T> response)` is invoked.
                Here, `T` stands for `AiMessage` in the case of `StreamingChatLanguageModel`,
                and `String` for `StreamingLanguageModel`. The `Response` object contains the complete response.
                - When an error occurs: `onError(Throwable error)` is invoke
                """,
                """
                Notice how we used the cheaper Llama2 for the simple task of identifying whether the text is a greeting or not,
                and the more expensive GPT-4 with a content retriever (RAG) for a more complex task.
                """
        );

        for (int GTCounter = 0; GTCounter < groundTurthChunks.size(); GTCounter++) {
            String groundTruthChunk = groundTurthChunks.get(GTCounter);
            for (int RetCounter = 0; RetCounter < retrievedChunks.size(); RetCounter++) {
                String retrievedChunk = retrievedChunks.get(RetCounter);
                for (Matcher matcher : matchers()) {
                    boolean matched = matcher.match(groundTruthChunk, List.of(retrievedChunk));
                    if (!matched) {
                        System.out.println("================================================================================");
                        System.out.println("groundTruthChunk=%s\nretrievedChunk=%s\nmatcher=%s\nmatched=%s"
                                .formatted(groundTruthChunk, retrievedChunk, matcher.getClass(), matched));
                    }
                }
            }
        }

    }

    static List<Matcher> matchers() {
        return List.of(
//                new RougeMatcher("l")
//                new TfIdfMatcher(),
                new SentenceMatcher()
        );
    }
}
