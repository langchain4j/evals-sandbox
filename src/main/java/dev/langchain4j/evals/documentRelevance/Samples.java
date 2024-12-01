package dev.langchain4j.evals.documentRelevance;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Samples {
    public static final List<List<String>> sampleGroundTruths = List.of(
            List.of("/tutorials/4-response-streaming.md", "/tutorials/5-ai-services.md"),
            List.of("/tutorials/4-response-streaming.md", "/tutorials/5-ai-services.md"));

    private static final String langchainDocsPath = "/home/dkafetzis/Documents/langchain4j/docs/docs";

    /**
     * Converts the content of a file located at a specific path to a string if the file exists.
     * If the file does not exist, returns the provided path string.
     *
     * @param possiblePath the potential path to the file whose content is to be read
     * @return the content of the file as a string if the file exists; otherwise, the original path string
     */
    public static String fileToContent(String possiblePath){
        File contentFile = new File(langchainDocsPath + possiblePath);
        if (contentFile.exists()) {
            StringBuilder content = new StringBuilder();
            try (Scanner scanner = new Scanner(contentFile)) {
                while (scanner.hasNextLine()) {
                    content.append(scanner.nextLine()).append(System.lineSeparator());
                }
            } catch (FileNotFoundException e) {
                System.out.println("The file could not be opened, this happened:\n" + e.getMessage());
            }
            return content.toString();
        } else {
            return possiblePath;
        }
    }

    public static final List<List<String>> sampleRetrievedDocuments = List.of(
            List.of("/tutorials/4-response-streaming.md", "/tutorials/5-ai-services.md"),
            List.of(
                """
                    Below is an example of how to implement streaming with `StreamingChatLanguageModel`:\\n" +
                    "```java\\n" +
                    "StreamingChatLanguageModel model = OpenAiStreamingChatModel.builder()\\n" +
                    "    .apiKey(System.getenv(\\"OPENAI_API_KEY\\"))\\n" +
                    "    .modelName(GPT_4_O_MINI)\\n" +
                    "    .build();\\n" +
                    "\\n" +
                    "String userMessage = \\"Tell me a joke\\";\\n" +
                    "\\n" +
                    "model.generate(userMessage, new StreamingResponseHandler<AiMessage>() {\\n" +
                    "\\n" +
                    "    @Override\\n" +
                    "    public void onNext(String token) {\\n" +
                    "        System.out.println(\\"onNext: \\" + token);\\n" +
                    "    }\\n" +
                    "\\n" +
                    "    @Override\\n" +
                    "    public void onComplete(Response<AiMessage> response) {\\n" +
                    "        System.out.println(\\"onComplete: \\" + response);\\n" +
                    "    }\\n" +
                    "\\n" +
                    "    @Override\\n" +
                    "    public void onError(Throwable error) {\\n" +
                    "        error.printStackTrace();\\n" +
                    "    }\\n" +
                    "});\\n" +
                    "```
                    """,
                    """
                    ```java
                    @SystemMessage("Given a name of a country, answer with a name of it's capital")
                    String chat(String userMessage);
                    
                    @SystemMessage("Given a name of a country, answer with a name of it's capital")
                    String chat(@UserMessage String userMessage);
                    
                    @SystemMessage("Given a name of a country, {{answerInstructions}}")
                    String chat(@V("answerInstructions") String answerInstructions, @UserMessage String userMessage);
                    
                    @SystemMessage("Given a name of a country, answer with a name of it's capital")
                    String chat(@UserMessage String userMessage, @V("country") String country); // userMessage contains "{{country}}" template variable
                    
                    @SystemMessage("Given a name of a country, {{answerInstructions}}")
                    String chat(@V("answerInstructions") String answerInstructions, @UserMessage String userMessage, @V("country") String country); // userMessage contains "{{country}}" template variable
                    
                    @SystemMessage("Given a name of a country, answer with a name of it's capital")
                    @UserMessage("Germany")
                    String chat();
                    
                    @SystemMessage("Given a name of a country, {{answerInstructions}}")
                    @UserMessage("Germany")
                    String chat(@V("answerInstructions") String answerInstructions);
                    
                    @SystemMessage("Given a name of a country, answer with a name of it's capital")
                    @UserMessage("{{it}}")
                    String chat(String country);
                    
                    @SystemMessage("Given a name of a country, answer with a name of it's capital")
                    @UserMessage("{{country}}")
                    String chat(@V("country") String country);
                    
                    @SystemMessage("Given a name of a country, {{answerInstructions}}")
                    @UserMessage("{{country}}")
                    String chat(@V("answerInstructions") String answerInstructions, @V("country") String country);
                    ```""",
                    """
                    Depending on the model and provider you choose, you can adjust numerous parameters that will define:
                    - The model's output: the level of creativity or determinism in the generated content (text, images),
                    the volume of content generated, etc.
                    - The connectivity: base URL, authorization keys, timeouts, retries, logging, etc.
                    
                    Typically, you will find all the parameters and their meaning on the model provider's website.
                    For example, OpenAI API's parameters can be found at https://platform.openai.com/docs/api-reference/chat
                    """,
                    """
                    ## Builder
                    We can set every available parameter of the model using the builder pattern as follows:
                    ```java
                    OpenAiChatModel model = OpenAiChatModel.builder()
                            .apiKey("demo")
                            .modelName("gpt-4")
                            .temperature(0.3)
                            .timeout(ofSeconds(60))
                            .logRequests(true)
                            .logResponses(true)
                            .build();
                    ```
                    """));
}
