package dev.langchain4j.evals;

import dev.langchain4j.data.document.Metadata;
import dev.langchain4j.data.segment.TextSegment;

import java.util.List;
import java.util.Map;

class Dataset {

    static List<DatasetEntry> get() {
        return List.of(
                //Changed the dataset entries to better match what comes from the embeddings store.
                //The current entries are using whole document texts for the context
                //A better approach would probably be to run some queries to the store to get text segments
                //and make dataset entries out of those to simulate more real world like data for an application dataset.
                //For the generation step we should also add an expected answer to each dataset entry.
                new DatasetEntry(
                        "How to use LangChain4j with Quarkus?",
                        List.of(
                                TextSegment.from("""
                               ---
                               sidebar_position: 24
                               ---

                               # Quarkus Integration

                               [Quarkus](https://quarkus.io/) provides a superb [extension for LangChain4j](https://github.com/quarkiverse/quarkus-langchain4j).

                               You can find all the necessary documentation [here](https://docs.quarkiverse.io/quarkus-langchain4j/dev/index.html).
                               """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs",
                                                "file_name", "intro.md"
                                        ))),
                                TextSegment.from("""
                                ---
                                 sidebar_position: 3
                                 ---
    
                                 # Quarkus
    
                                 The Quarkus [LangChain4j extension](https://quarkus.io/extensions/io.quarkiverse.langchain4j/quarkus-langchain4j-core/) seamlessly integrates with the Quarkus programming model and existing Quarkus runtime components.
    
                                 The extension offers the following advantages over using the vanilla LangChain4j library in Quarkus:
    
                                 - Integration with the Quarkus programming model
                                     - A new `@RegisterAiService` annotation for declarative AI services
                                     - Injectable CDI beans for the LangChain4j models
                                 - Ability to compile to a GraalVM native binary
                                 -Standard configuration properties for configuring models
                                 - Built-in observability (metrics, tracing, and auditing)
                                 - Build-time wiring. Doing more at build-time reduces the footprint of the LangChain4j library and enables build-time usability hints.
    
                                 ## Dev UI
    
                                 In Dev mode, the quarkus-langchain4j project provides several pages in the Dev UI to facilitate LangChain4j development:
                                """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        )
                                )),
                                TextSegment.from("""
                                        ## LangChain4j Repositories
                                        - [Main repository](https://github.com/langchain4j/langchain4j)
                                        - [Quarkus extension](https://github.com/quarkiverse/quarkus-langchain4j)
                                        - [Spring Boot integration](https://github.com/langchain4j/langchain4j-spring)
                                        - [Community integrations](https://github.com/langchain4j/langchain4j-community)
                                        - [Examples](https://github.com/langchain4j/langchain4j-examples)
                                        - [Community resources](https://github.com/langchain4j/langchain4j-community-resources)
                                        - [In-process embeddings](https://github.com/langchain4j/langchain4j-embeddings)
                                        
                                        ## Use Cases
                                        You might ask why would I need all of this?
                                        Here are some examples:
                                        """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        )
                                )),
                                TextSegment.from("""
                                        ---
                                        sidebar_position: 5
                                        ---
                                        
                                        # Get Started
                                        
                                        :::note
                                        If you are using Quarkus, see [Quarkus Integration](/tutorials/quarkus-integration/).
                                        
                                        If you are using Spring Boot, see [Spring Boot Integration](/tutorials/spring-boot-integration).
                                        :::
                                        
                                        LangChain4j offers [integration with many LLM providers](/integrations/language-models/).
                                        Each integration has its own maven dependency.
                                        The simplest way to begin is with the OpenAI integration:
                                        
                                        - For Maven in `pom.xml`:
                                        ```xml
                                        <dependency>
                                            <groupId>dev.langchain4j</groupId>
                                            <artifactId>langchain4j-open-ai</artifactId>
                                            <version>0.35.0</version>
                                        </dependency>
                                        ```
                                        
                                        If you wish to use a high-level [AI Services](/tutorials/ai-services) API, you will also need to add\s
                                        the following dependency:
                                        
                                        ```xml
                                        <dependency>
                                            <groupId>dev.langchain4j</groupId>
                                            <artifactId>langchain4j</artifactId>
                                            <version>0.35.0</version>
                                        </dependency>
                                        ```
                                        """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        )
                                ))
                        ),
                        """
                                    To use LangChain4j with Quarkus, follow these steps:
                                
                                1. **Add the Dependency**: Include the Quarkus LangChain4j extension in your project's build configuration (e.g., Maven or Gradle).
                                
                                   For Maven, add the following dependency in your `pom.xml`:
                                   ```xml
                                   <dependency>
                                       <groupId>io.quarkiverse.langchain4j</groupId>
                                       <artifactId>quarkus-langchain4j-core</artifactId>
                                   </dependency>
                                   ```
                                
                                   For Gradle, add the dependency in your `build.gradle`:
                                   ```groovy
                                   implementation 'io.quarkiverse.langchain4j:quarkus-langchain4j-core'
                                   ```
                                
                                2. **Use Annotations**: Utilize the `@RegisterAiService` annotation to declare AI services in your application. This allows you to integrate AI functionality declaratively.
                                
                                3. **Inject CDI Beans**: You can inject LangChain4j models as CDI beans in your Quarkus application, making it easier to manage dependencies and lifecycle.
                                
                                4. **Configure Models**: Set up standard configuration properties for the LangChain4j models in your `application.properties` file. This is where you can define model-specific settings.
                                
                                5. **Build-Time Optimization**: Take advantage of Quarkus' build-time wiring to optimize the footprint of the LangChain4j library and enable build-time usability hints.
                                
                                6. **Run in Dev Mode**: Start your application in dev mode using the command:
                                   ```bash
                                   ./mvnw quarkus:dev
                                   ```
                                   This will allow you to leverage the Dev UI provided by the quarkus-langchain4j extension.
                                
                                7. **Explore the Dev UI**: Use the Dev UI to access various features:
                                   - **AI Services Page**: View all detected AI services and tools.
                                   - **Embeddings Store Access**: Add and search embeddings.
                                   - **Tools Page**: See a list of detected tools.
                                   - **Chat Page**: Manually interact with a chat model if available.
                                   - **Images Page**: Test outputs of image models.
                                   - **Moderation Page**: Test outputs of moderation models.
                                
                                8. **Refer to Documentation**: For more detailed information, check the [Quarkus LangChain4j documentation](https://docs.quarkiverse.io/quarkus-langchain4j/dev/) to explore additional features and configurations.
                                
                                By following these steps, you can effectively integrate and utilize LangChain4j within your Quarkus application."""
                )
//                new DatasetEntry(
//                        "What LLMs does langchain4j support?",
//                        List.of(
//                                new TextSegment(
//                                        """
//                                                | Provider                                                                         | [Streaming](/tutorials/response-streaming) | [Tools](/tutorials/tools) | [JSON mode](/tutorials/ai-services#json-mode) | Supported Modalities (Input)   | [Observability](/tutorials/observability) | Local                                             | Native | Comments                    |
//                                                |----------------------------------------------------------------------------------|--------------------------------------------|---------------------------|-----------------------------------------------|--------------------------------|-------------------------------------------|---------------------------------------------------|--------|-----------------------------|
//                                                | [Amazon Bedrock](/integrations/language-models/amazon-bedrock)                   | ✅                                          | ✅                         |                                               | text                           | ✅                                         |                                                   |        |                             |
//                                                | [Anthropic](/integrations/language-models/anthropic)                             | ✅                                          | ✅                         |                                               | text, image                    |                                           |                                                   | ✅      |                             |
//                                                | [Azure OpenAI](/integrations/language-models/azure-open-ai)                      | ✅                                          | ✅                         | ✅                                             | text, image                    | ✅                                         |                                                   |        |                             |
//                                                | [ChatGLM](/integrations/language-models/chatglm)                                 |                                            |                           |                                               | text                           |                                           |                                                   |        |                             |
//                                                | [DashScope](/integrations/language-models/dashscope)                             | ✅                                          | ✅                         |                                               | text, image, audio             | ✅                                         |                                                   |        |                             |
//                                                | [GitHub Models](/integrations/language-models/github-models)                     | ✅                                          | ✅                         | ✅                                             | text                           | ✅                                         |                                                   |        |                             |
//                                                | [Google AI Gemini](/integrations/language-models/google-ai-gemini)               | ✅                                          | ✅                         | ✅                                             | text, image, audio, video, PDF | ✅                                         |                                                   |        |                             |
//                                                | [Google Vertex AI Gemini](/integrations/language-models/google-vertex-ai-gemini) | ✅                                          | ✅                         | ✅                                             | text, image, audio, video, PDF | ✅                                         |                                                   |        |                             |
//                                                | [Google Vertex AI PaLM 2](/integrations/language-models/google-palm)             |                                            |                           |                                               | text                           |                                           |                                                   | ✅      |                             |
//                                                | [Hugging Face](/integrations/language-models/hugging-face)                       |                                            |                           |                                               | text                           |                                           |                                                   |        |                             |
//                                                | [Jlama](/integrations/language-models/jlama)                                     | ✅                                          | ✅                         |                                               | text                           |                                           | ✅                                                 | ✅      |                             |
//                                                | [LocalAI](/integrations/language-models/local-ai)                                | ✅                                          | ✅                         |                                               | text                           |                                           | ✅                                                 |        |                             |
//                                                | [Mistral AI](/integrations/language-models/mistral-ai)                           | ✅                                          | ✅                         | ✅                                             | text                           |                                           |                                                   |        |                             |
//                                                | [Ollama](/integrations/language-models/ollama)                                   | ✅                                          | ✅                         | ✅                                             | text, image                    | ✅                                         | ✅                                                 |        |                             |
//                                                | [OpenAI](/integrations/language-models/open-ai)                                  | ✅                                          | ✅                         | ✅                                             | text, image                    | ✅                                         | Compatible with: Ollama, LM Studio, GPT4All, etc. | ✅      | Compatible with: Groq, etc. |
//                                                | [Qianfan](/integrations/language-models/qianfan)                                 | ✅                                          | ✅                         |                                               | text                           |                                           |                                                   |        |                             |
//                                                | [Cloudflare Workers AI](/integrations/language-models/workers-ai)                |                                            |                           |                                               | text                           |                                           |                                                   |        |                             |
//                                                | [Zhipu AI](/integrations/language-models/zhipu-ai)                               | ✅                                          | ✅                         |                                               | text, image                    | ✅                                         |                                                   |        |                             |
//                                                """
//                                        , new Metadata(Map.of(
//                                                "absolute_directory_path","evals/../langchain4j/docs/docs/integrations/language-models",
//                                                "file_name","index.md"
//                                ))
//                                )
//                        ),
//                        "yes"
//                ),
//                new DatasetEntry("How can I implement the anthropic tokenizer?",
//                        List.of(
//                                new TextSegment("""
//                                        | Provider                                                                         | [Streaming](/tutorials/response-streaming) | [Tools](/tutorials/tools) | [JSON mode](/tutorials/ai-services#json-mode) | Supported Modalities (Input)   | [Observability](/tutorials/observability) | Local                                             | Native | Comments                    |\n" +
//                                        "|----------------------------------------------------------------------------------|--------------------------------------------|---------------------------|-----------------------------------------------|--------------------------------|-------------------------------------------|---------------------------------------------------|--------|-----------------------------|\n" +
//                                        "| [Amazon Bedrock](/integrations/language-models/amazon-bedrock)                   | ✅                                          | ✅                         |                                               | text                           | ✅                                         |                                                   |        |                             |\n" +
//                                        "| [Anthropic](/integrations/language-models/anthropic)                             | ✅                                          | ✅                         |                                               | text, image                    |
//                                        """, new Metadata(
//                                        Map.of(
//                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
//                                                "file_name", "quarkus.md"
//                                        ))),
//                                new TextSegment("""
//                                        # Anthropic
//
//                                        - [Anthropic Documentation](https://docs.anthropic.com/claude/docs)
//                                        - [Anthropic API Reference](https://docs.anthropic.com/claude/reference)
//
//                                        ## Maven Dependency
//
//                                        ```xml
//                                        <dependency>
//                                            <groupId>dev.langchain4j</groupId>
//                                            <artifactId>langchain4j-anthropic</artifactId>
//                                            <version>0.35.0</version>
//                                        </dependency>
//                                        ```
//
//                                        ## AnthropicChatModel
//
//                                        ```java
//                                        AnthropicChatModel model = AnthropicChatModel.builder()
//                                            .apiKey(System.getenv("ANTHROPIC_API_KEY"))
//                                            .modelName(CLAUDE_3_5_SONNET_20240620)
//                                            .build();
//                                        String answer = model.generate("Say 'Hello World'");
//                                        System.out.println(answer);
//                                        ```
//
//                                        ### Customizing AnthropicChatModel
//                                        ```java
//                                        AnthropicChatModel model = AnthropicChatModel.builder()
//                                            .baseUrl(...)
//                                            .apiKey(...)
//                                            .version(...)
//                                            .beta(...)
//                                            .modelName(...)
//                                            .temperature(...)
//                                            .topP(...)
//                                            .topK(...)
//                                            .maxTokens(...)
//                                            .stopSequences(...)
//                                            .timeout(...)
//                                            .maxRetries(...)
//                                            .logRequests(...)
//                                            .logResponses(...)
//                                            .build();
//                                        ```
//                                        """, new Metadata(
//                                        Map.of(
//                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
//                                                "file_name", "quarkus.md"
//                                        )))
//                        ),
//                        "To implement the Anthropic tokenizer, you can follow these steps:\\n\\n1. **Add the Maven Dependency**: Include the necessary dependency in your `pom.xml` file to use the Anthropic library.\\n\\n   ```xml\\n   <dependency>\\n       <groupId>dev.langchain4j</groupId>\\n       <artifactId>langchain4j-anthropic</artifactId>\\n       <version>0.34.0</version>\\n   </dependency>\\n   ```\\n\\n2. **Create an Instance of the AnthropicChatModel**: Use the following Java code to create an instance of the `AnthropicChatModel` using your API key.\\n\\n   ```java\\n   AnthropicChatModel model = AnthropicChatModel.withApiKey(System.getenv(\\\"ANTHROPIC_API_KEY\\\"));\\n   ```\\n\\n3. **Generate Tokens**: Use the `generate` method to get a response. You can also implement a streaming response handler to handle tokens as they are generated.\\n\\n   ```java\\n   model.generate(\\\"Say 'Hello World'\\\", new StreamingResponseHandler<AiMessage>() {\\n       @Override\\n       public void onNext(String token) {\\n           // This method is called when a new token is available\\n       }\\n\\n       @Override\\n       public void onComplete(Response<AiMessage> response) {\\n           // This method is called when the model has completed responding\\n       }\\n\\n       @Override\\n       public void onError(Throwable error) {\\n           // This method is called when an error occurs\\n       }\\n   });\\n   ```\\n\\n4. **Refer to Documentation**: For further customization and options, you can refer to the [Anthropic Documentation](https://docs.anthropic.com/claude/docs) and the [API Reference](https://docs.anthropic.com/claude/reference).\\n\\nThis should help you implement the Anthropic tokenizer in your application."),
//                new DatasetEntry("If I receive a 200.000 or more token size super big text, how can I handle a multicall logic to the llm so it can manage big texts? because otherwise if I send a super big text that is 200K or bigger I will get an error",
//                        List.of(
//                                new TextSegment("""
//                                        LLMs generate text one token at a time, so many LLM providers offer a way to stream the response
//                                        token-by-token instead of waiting for the entire text to be generated.
//                                        This significantly improves the user experience, as the user does not need to wait an unknown
//                                        amount of time and can start reading the response almost immediately.
//
//                                        For the `ChatLanguageModel` and `LanguageModel` interfaces, there are corresponding
//                                        `StreamingChatLanguageModel` and `StreamingLanguageModel` interfaces.
//                                        These have a similar API but can stream the responses.
//                                        They accept an implementation of the `StreamingResponseHandler` interface as an argument.
//
//                                        ```java
//                                        public interface StreamingResponseHandler<T> {
//
//                                            void onNext(String token);
//                                        \s
//                                            default void onComplete(Response<T> response) {}
//
//                                            void onError(Throwable error);
//                                        }
//                                        ```
//
//                                        By implementing `StreamingResponseHandler`, you can define actions for the following events:
//                                        - When the next token is generated: `onNext(String token)` is invoked.
//                                        For instance, you can send the token directly to the UI as soon as it becomes available.
//                                        - When the LLM has completed generation: `onComplete(Response<T> response)` is invoked.
//                                        Here, `T` stands for `AiMessage` in the case of `StreamingChatLanguageModel`,
//                                        and `String` for `StreamingLanguageModel`. The `Response` object contains the complete response.
//                                        - When an error occurs: `onError(Throwable error)` is invoked.
//                                        """, new Metadata(
//                                        Map.of(
//                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
//                                                "file_name", "quarkus.md"
//                                        ))),
//                                new TextSegment("""
//                                        ## AI Services
//
//                                        We propose another solution called AI Services, tailored for Java.
//                                        The idea is to hide the complexities of interacting with LLMs and other components behind a simple API.
//
//                                        This approach is very similar to Spring Data JPA or Retrofit: you declaratively define an interface with the desired API,
//                                        and LangChain4j provides an object (proxy) that implements this interface.
//                                        You can think of AI Service as a component of the service layer in your application.
//                                        It provides _AI_ services. Hence the name.
//
//                                        AI Services handle the most common operations:
//                                        - Formatting inputs for the LLM
//                                        - Parsing outputs from the LLM
//
//                                        They also support more advanced features:
//                                        - Chat memory
//                                        - Tools
//                                        - RAG""", new Metadata(
//                                        Map.of(
//                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
//                                                "file_name", "quarkus.md"
//                                        )))
//
//                        ),
//                        "To handle a super big text of 200,000 tokens or more when working with a large language model (LLM), you can adopt a multi-call approach and utilize response streaming. Here’s how you can manage this:\\n\\n1. **Break Down the Text**: Divide the super big text into smaller manageable chunks that fit within the token limit of the LLM. This allows you to process the text without hitting the size limit.\\n\\n2. **Use Streaming Response**: Instead of waiting for the entire response, you can implement response streaming. This allows the LLM to generate text one token at a time. By using the `StreamingResponseHandler`, you can handle the tokens as they are generated, which enhances the user experience as they can start receiving information almost immediately.\\n\\n3. **Implementing Streaming**: You can set up a `StreamingResponseHandler` which will manage the incoming tokens. Each time a token is generated, the `onNext` method will be called, and you can process that token accordingly. Here's an example setup:\\n\\n   ```java\\n   QianfanStreamingChatModel qianfanStreamingChatModel = QianfanStreamingChatModel.builder()\\n       .apiKey(\\\"apiKey\\\")\\n       .secretKey(\\\"secretKey\\\")\\n       .modelName(\\\"Yi-34B-Chat\\\")\\n       .build();\\n\\n   // For each chunk of text\\n   qianfanStreamingChatModel.generate(chunk, new StreamingResponseHandler<AiMessage>() {\\n       @Override\\n       public void onNext(String token) {\\n           // Process the token (e.g., append to a result string)\\n       }\\n\\n       @Override\\n       public void onComplete(Response<AiMessage> response) {\\n           // Handle completion of the response\\n       }\\n\\n       @Override\\n       public void onError(Throwable error) {\\n           // Handle any errors\\n       }\\n   });\\n   ```\\n\\n4. **Chaining Logic**: If your application requires complex logic, ensure to break it down into smaller parts. This is crucial to avoid overloading the LLM with too many instructions at once, which may lead to inefficiencies or errors.\\n\\nBy following these steps, you can effectively manage large texts with an LLM while minimizing the risk of errors due to token size limits.")
//                new DatasetEntry(
//                        "give me a exemple of web socket api that stream the model output?",
//                        Map.of(
//                                "/tutorials/4-response-streaming.md:\nBelow is an example of how to implement streaming with `StreamingChatLanguageModel`:\n" +
//                                        "```java\n" +
//                                        "StreamingChatLanguageModel model = OpenAiStreamingChatModel.builder()\n" +
//                                        "    .apiKey(System.getenv(\"OPENAI_API_KEY\"))\n" +
//                                        "    .modelName(GPT_4_O_MINI)\n" +
//                                        "    .build();\n" +
//                                        "\n" +
//                                        "String userMessage = \"Tell me a joke\";\n" +
//                                        "\n" +
//                                        "model.generate(userMessage, new StreamingResponseHandler<AiMessage>() {\n" +
//                                        "\n" +
//                                        "    @Override\n" +
//                                        "    public void onNext(String token) {\n" +
//                                        "        System.out.println(\"onNext: \" + token);\n" +
//                                        "    }\n" +
//                                        "\n" +
//                                        "    @Override\n" +
//                                        "    public void onComplete(Response<AiMessage> response) {\n" +
//                                        "        System.out.println(\"onComplete: \" + response);\n" +
//                                        "    }\n" +
//                                        "\n" +
//                                        "    @Override\n" +
//                                        "    public void onError(Throwable error) {\n" +
//                                        "        error.printStackTrace();\n" +
//                                        "    }\n" +
//                                        "});\n" +
//                                        "```", 0.5,
//                                "/tutorials/5-ai-services.md:\n```java\n" +
//                                        "@SystemMessage(\"Given a name of a country, answer with a name of it's capital\")\n" +
//                                        "String chat(String userMessage);\n" +
//                                        "\n" +
//                                        "@SystemMessage(\"Given a name of a country, answer with a name of it's capital\")\n" +
//                                        "String chat(@UserMessage String userMessage);\n" +
//                                        "\n" +
//                                        "@SystemMessage(\"Given a name of a country, {{answerInstructions}}\")\n" +
//                                        "String chat(@V(\"answerInstructions\") String answerInstructions, @UserMessage String userMessage);\n" +
//                                        "\n" +
//                                        "@SystemMessage(\"Given a name of a country, answer with a name of it's capital\")\n" +
//                                        "String chat(@UserMessage String userMessage, @V(\"country\") String country); // userMessage contains \"{{country}}\" template variable\n" +
//                                        "\n" +
//                                        "@SystemMessage(\"Given a name of a country, {{answerInstructions}}\")\n" +
//                                        "String chat(@V(\"answerInstructions\") String answerInstructions, @UserMessage String userMessage, @V(\"country\") String country); // userMessage contains \"{{country}}\" template variable\n" +
//                                        "\n" +
//                                        "@SystemMessage(\"Given a name of a country, answer with a name of it's capital\")\n" +
//                                        "@UserMessage(\"Germany\")\n" +
//                                        "String chat();\n" +
//                                        "\n" +
//                                        "@SystemMessage(\"Given a name of a country, {{answerInstructions}}\")\n" +
//                                        "@UserMessage(\"Germany\")\n" +
//                                        "String chat(@V(\"answerInstructions\") String answerInstructions);\n" +
//                                        "\n" +
//                                        "@SystemMessage(\"Given a name of a country, answer with a name of it's capital\")\n" +
//                                        "@UserMessage(\"{{it}}\")\n" +
//                                        "String chat(String country);\n" +
//                                        "\n" +
//                                        "@SystemMessage(\"Given a name of a country, answer with a name of it's capital\")\n" +
//                                        "@UserMessage(\"{{country}}\")\n" +
//                                        "String chat(@V(\"country\") String country);\n" +
//                                        "\n" +
//                                        "@SystemMessage(\"Given a name of a country, {{answerInstructions}}\")\n" +
//                                        "@UserMessage(\"{{country}}\")\n" +
//                                        "String chat(@V(\"answerInstructions\") String answerInstructions, @V(\"country\") String country);", 0.5
//                        )
//                )
//                new DatasetEntry(
//                        "give me a example of a web socket api that stream the model output using Ktor framework",
//                        Map.of(
//                                "/tutorials/4-response-streaming.md", 0.5,
//                                "/tutorials/5-ai-services.md", 0.5
//                        )
//                ),
//                new DatasetEntry(
//                        "What LLMs does langchain4j support?",
//                        Map.of(
//                                "/integrations/language-models/index.md", 1.0
//                        )
//                ),
//                new DatasetEntry(
//                        "Does it support ollama?",
//                        Map.of(
//                                "/integrations/language-models/index.md", 1.0,
//                                "/integrations/language-models/ollama.md", 1.0
//                        )
//                ),
//                new DatasetEntry(
//                        "what maven dependency do I need for ollama?",
//                        Map.of(
//                                "/integrations/language-models/ollama.md", 1.0
//                        )
//                ),
//                new DatasetEntry(
//                        "I wanna log request sent to model using Langchain4j. how can I do?",
//                        Map.of(
//                                "/tutorials/logging.md", 1.0
//                        )
//                ),
//                new DatasetEntry(
//                        "what log level I have to set?",
//                        Map.of(
//                                "/tutorials/logging.md", 1.0
//                        )
//                ),
//                new DatasetEntry(
//                        "How do I add OpenAi to my spring boot project?",
//                        Map.of(
//                                "/tutorials/spring-boot-integration.md", 1.0,
//                                "/integrations/language-models/open-ai.md", 1.0
//                        )
//                ),
//                new DatasetEntry(
//                        "How to use LangChain4j with Quarkus?",
//                        Map.of(
//                                "/tutorials/quarkus-integration.md", 1.0,
//                                "/integrations/frameworks/quarkus.md", 1.0
//                        )
//                ),
//                new DatasetEntry(
//                        "how do I create a chat with Gemini?",
//                        Map.of(
//                                "/integrations/language-models/google-ai-gemini.md", 1.0,
//                                "/integrations/language-models/google-vertex-ai-gemini.md", 1.0
//                        )
//                ),
//                new DatasetEntry(
//                        "Can I send a file using langchain ?",
//                        Map.of(
//                                "/tutorials/7-rag.md", 1.0
//                        )
//                ),
//                new DatasetEntry(
//                        "how do i configure ollama?",
//                        Map.of(
//                                "/integrations/language-models/ollama.md", 1.0
//                        )
//                ),
//                new DatasetEntry(
//                        "What maven dependency does ollama need?",
//                        Map.of(
//                                "/integrations/language-models/ollama.md", 1.0
//                        )
//                ),
//                new DatasetEntry(
//                        "What Maven dependencies do I need for Open AI and spring boot?",
//                        Map.of(
//                                "/tutorials/spring-boot-integration.md", 1.0,
//                                "/integrations/language-models/open-ai.md", 1.0
//                        )
//                ),
//                new DatasetEntry(
//                        "What is langchain4j",
//                        Map.of(
//                                "/intro.md", 1.0
//                        )
//                ),
//                new DatasetEntry(
//                        "How to customize ChatMemoryStore",
//                        Map.of(
//                                "/tutorials/2-chat-memory.md", 1.0
//                        )
//                ),
//                new DatasetEntry(
//                        "What does List<ChatMessage> messages contain in the updateMessages function?",
//                        Map.of(
//                                "/tutorials/2-chat-memory.md", 1.0
//                        )
//                ),
//                new DatasetEntry(
//                        "For this ChatMemoryStore interface, should I return the message of the user's question, or the message containing the systemmessage, prompt, or assistant's answer? Which messages should I return only to be enough for LLM to understand the context?",
//                        Map.of(
//                                "/tutorials/2-chat-memory.md", 1.0
//                        )
//                ),
//                new DatasetEntry(
//                        "Is the content of SystemMessage sent for every request? What is the difference between this and directly using prompt + user message to send to gpt?",
//                        Map.of(
//                                "/tutorials/2-chat-memory.md", 1.0
//                        )
//                ),
//                new DatasetEntry(
//                        "Don't you mean that SystemMessage will also be sent every time a request is made, and SystemMessage also consumes tokens?",
//                        Map.of(
//                                "/tutorials/2-chat-memory.md", 1.0
//                        )
//                ),
//                new DatasetEntry(
//                        "I'm calculating embeddings using local models like AllMiniLmL6V2EmbeddingModel. Is there a way to leverage GPU?",
//                        Map.of(
//                                "/integrations/embedding-models/1-in-process.md", 1.0
//                        )
//                ),
//                new DatasetEntry(
//                        "how to compute embeddings using GPU instead of CPU",
//                        Map.of(
//                                "/integrations/embedding-models/1-in-process.md", 1.0
//                        )
//                ),
//                new DatasetEntry(
//                        "Why can't I see any logs in the console after I configured it?",
//                        Map.of(
//                                "/tutorials/logging.md", 1.0
//                        )
//                ),
//                new DatasetEntry(
//                        "How can I do embedding rag?",
//                        Map.of(
//                                "/tutorials/7-rag.md", 1.0
//                        )
//                ),
//                new DatasetEntry(
//                        "How can I make an LLM configuration to return always a JSON object as a response?",
//                        Map.of(
//                                "/tutorials/5-ai-services.md", 1.0
//                        )
//                ),
//                new DatasetEntry(
//                        "Which property is the one that sets a model to return always a json?",
//                        Map.of(
//                                "/tutorials/5-ai-services.md", 1.0
//                        )
//                ),
//                new DatasetEntry(
//                        "Hi, is there a way to use langsmith together with langchain4j? Or some other way to get insight into what is happening with your chains?",
//                        Map.of(
//                                "/tutorials/observability.md", 1.0
//                        )
//                ),
//                new DatasetEntry(
//                        "Is langchain4j unable to load online documents?",
//                        Map.of(
//                                "/tutorials/7-rag.md", 1.0
//                        )
//                ),
//                new DatasetEntry(
//                        "What are AllMiniLmL6V2EmbeddingModel and BgeSmallEnV15QuantizedEmbeddingModel in langchain4j",
//                        Map.of(
//                                "/integrations/embedding-models/1-in-process.md", 1.0
//                        )
//                ),
//                new DatasetEntry(
//                        "In Langchain4j, I have a prompt text. Should I put it in UserMessage or SystemMessage? Is there any difference between the two?",
//                        Map.of(
//                                "/tutorials/1-chat-and-language-models.md", 1.0
//                        )
//                ),
//                new DatasetEntry(
//                        "What is the logic of chatMemory in langchain4j? .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(5)); How is it stored? What does the 5 mean?",
//                        Map.of(
//                                "/tutorials/2-chat-memory.md", 1.0
//                        )
//                ),
//                new DatasetEntry(
//                        "In langchain4j, AiServices<Agent> aiServices = AiServices.builder(Agent.class)\n" +
//                                ".streamingChatLanguageModel(model)\n" +
//                                ".chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(20));\n" +
//                                "What does the memoryId corresponding to this chatMemoryProvider represent? Why is there no place to configure memoryId?",
//                        Map.of(
//                                "/tutorials/2-chat-memory.md", 0.5,
//                                "/tutorials/5-ai-services.md", 0.5
//                        )
//                ),
//                new DatasetEntry(
//                        "Why is the memoryId obtained from InMemoryChatMemoryStore still default after I specify the memoryId?",
//                        Map.of(
//                                "/tutorials/2-chat-memory.md", 0.5,
//                                "/tutorials/5-ai-services.md", 0.5
//                        )
//                ),
//                new DatasetEntry(
//                        "How to get the document metadata retrieved by embedding in langchain4j",
//                        Map.of(
//                                "/tutorials/7-rag.md", 1.0
//                        )
//                ),
//                new DatasetEntry(
//                        "Hello, can you create me a code example of a chat agent, who can make groups of a list? using tool calls",
//                        Map.of(
//                                "/tutorials/5-ai-services.md", 1.0
//                        )
//                )
        );
    }
}
