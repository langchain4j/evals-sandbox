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
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/tutorials",
                                                "file_name", "quarkus-integration.md"
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
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs",
                                                "file_name", "intro.md"
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
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/tutorials",
                                                "file_name", "logging.md"
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
                ),
                new DatasetEntry(
                        "What LLMs does langchain4j support?",
                        List.of(
                                new TextSegment(
                                """
                                      Here's how:
                                      1. **Unified APIs:**
                                      LLM providers (like OpenAI or Google Vertex AI) and embedding (vector) stores (such as Pinecone or Milvus)
                                      use proprietary APIs. LangChain4j offers a unified API to avoid the need for learning and implementing specific APIs for each of them.
                                      To experiment with different LLMs or embedding stores, you can easily switch between them without the need to rewrite your code.
                                      LangChain4j currently supports [15+ popular LLM providers](/integrations/language-models/)
                                      and [20+ embedding stores](/integrations/embedding-stores/).
                                      2. **Comprehensive Toolbox:**
                                      Since early 2023, the community has been building numerous LLM-powered applications,
                                      identifying common abstractions, patterns, and techniques. LangChain4j has refined these into a ready to use package.
                                      Our toolbox includes tools ranging from low-level prompt templating, chat memory management, and function calling
                                      to high-level patterns like AI Services and RAG.
                                      """
                                        , new Metadata(Map.of(
                                                "absolute_directory_path","evals/../langchain4j/docs/docs",
                                                "file_name","intro.md"
                                ))
                                ),
                                new TextSegment(
                                        """
                                            ## LangChain4j Features
                                            - Integration with [15+ LLM providers](/integrations/language-models)
                                            - Integration with [20+ embedding (vector) stores](/integrations/embedding-stores)
                                            - Integration with [15+ embedding models](/category/embedding-models)
                                            - Integration with [5 image generation models](/category/image-models)
                                            - Integration with [2 scoring (re-ranking) models](/category/scoring-reranking-models)
                                            - Integration with one moderation model (OpenAI)
                                            - Support for texts and images as inputs (multimodality)
                                            - [AI Services](/tutorials/ai-services) (high-level LLM API)
                                            - Prompt templates
                                            - Implementation of persistent and in-memory [chat memory](/tutorials/chat-memory) algorithms: message window and token window
                                            - [Streaming of responses from LLMs](/tutorials/response-streaming)
                                            - Output parsers for common Java types and custom POJOs
                                            - [Tools (function calling)](/tutorials/tools)
                                            - Dynamic Tools (execution of dynamically generated LLM code)
                                            """
                                        , new Metadata(Map.of(
                                        "absolute_directory_path","evals/../langchain4j/docs/docs",
                                        "file_name","intro.md"
                                ))
                                ),
                                new TextSegment(
                                        """
                                            ---
                                            sidebar_position: 1
                                            title: Introduction
                                            ---

                                            # Introduction

                                            Welcome!

                                            The goal of LangChain4j is to simplify integrating LLMs into Java applications.
                                            """
                                        , new Metadata(Map.of(
                                        "absolute_directory_path","evals/../langchain4j/docs/docs",
                                        "file_name","intro.md"
                                ))
                                ),
                                new TextSegment(
                                        """
                                            ## 2 levels of abstraction
                                            LangChain4j operates on two levels of abstraction:
                                            - Low level. At this level, you have the most freedom and access to all the low-level components such as
                                            [ChatLanguageModel](/tutorials/chat-and-language-models), `UserMessage`, `AiMessage`, `EmbeddingStore`, `Embedding`, etc.
                                            These are the "primitives" of your LLM-powered application.
                                            You have complete control over how to combine them, but you will need to write more glue code.
                                            - High level. At this level, you interact with LLMs using high-level APIs like [AI Services](/tutorials/ai-services),
                                            which hides all the complexity and boilerplate from you.
                                            You still have the flexibility to adjust and fine-tune the behavior, but it is done in a declarative manner.

                                            [![](/img/langchain4j-components.png)](/intro)
                                            """
                                        , new Metadata(Map.of(
                                        "absolute_directory_path","evals/../langchain4j/docs/docs",
                                        "file_name","intro.md"
                                ))
                                ),
                                new TextSegment(
                                        """
                                            ---
                                            sidebar_position: 6
                                            ---

                                            # AI Services

                                            So far, we have been covering low-level components like `ChatLanguageModel`, `ChatMessage`, `ChatMemory`, etc.
                                            Working at this level is very flexible and gives you total freedom, but it also forces you to write a lot of boilerplate code.
                                            Since LLM-powered applications usually require not just a single component but multiple components working together
                                            (e.g., prompt templates, chat memory, LLMs, output parsers, RAG components: embedding models and stores)
                                            and often involve multiple interactions, orchestrating them all becomes even more cumbersome.

                                            We want you to focus on business logic, not on low-level implementation details.
                                            Thus, there are currently two high-level concepts in LangChain4j that can help with that: AI Services and Chains.

                                            ## Chains (legacy)
                                            """
                                        , new Metadata(Map.of(
                                        "absolute_directory_path","evals/../langchain4j/docs/docs",
                                        "file_name","intro.md"
                                ))
                                )
                        ),
                        "yes"
                ),
                new DatasetEntry("How can I implement the anthropic tokenizer?",
                        List.of(
                                new TextSegment("""
                                        ### Customizing AnthropicChatModel
                                        ```java
                                        AnthropicChatModel model = AnthropicChatModel.builder()
                                            .baseUrl(...)
                                            .apiKey(...)
                                            .version(...)
                                            .beta(...)
                                            .modelName(...)
                                            .temperature(...)
                                            .topP(...)
                                            .topK(...)
                                            .maxTokens(...)
                                            .stopSequences(...)
                                            .timeout(...)
                                            .maxRetries(...)
                                            .logRequests(...)
                                            .logResponses(...)
                                            .build();
                                        ```
                                        See the description of some of the parameters above [here](https://docs.anthropic.com/claude/reference/messages_post).

                                        ## AnthropicStreamingChatModel
                                        ```java
                                        AnthropicStreamingChatModel model = AnthropicStreamingChatModel.builder()
                                            .apiKey(System.getenv("ANTHROPIC_API_KEY"))
                                            .modelName(CLAUDE_3_5_SONNET_20240620)
                                            .build();

                                        model.generate("Say 'Hello World'", new StreamingResponseHandler<AiMessage>() {

                                        @Override
                                            public void onNext(String token) {
                                                // this method is called when a new token is available
                                            }
                                        """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                        ### Plain Java
                                        ```java
                                        Tokenizer tokenizer = new OpenAiTokenizer();
                                        // or
                                        Tokenizer tokenizer = new OpenAiTokenizer("gpt-4o");
                                        ```

                                        ### Spring Boot
                                        The `OpenAiTokenizer` bean is created automatically by the Spring Boot starter.

                                        ## Examples
                                        - [OpenAI Examples](https://github.com/langchain4j/langchain4j-examples/tree/main/open-ai-examples/src/main/java)""", new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                        Configure `AnthropicChatModel` bean:
                                        ```
                                        langchain4j.anthropic.chat-model.api-key = ${ANTHROPIC_API_KEY}
                                        ```

                                        Configure `AnthropicStreamingChatModel` bean:
                                        ```
                                        langchain4j.anthropic.streaming-chat-model.api-key = ${ANTHROPIC_API_KEY}
                                        ```

                                        ## Examples

                                        - [AnthropicChatModelTest](https://github.com/langchain4j/langchain4j-examples/blob/main/anthropic-examples/src/main/java/AnthropicChatModelTest.java)
                                        - [AnthropicStreamingChatModelTest](https://github.com/langchain4j/langchain4j-examples/blob/main/anthropic-examples/src/main/java/AnthropicStreamingChatModelTest.java)
                                        - [AnthropicToolsTest](https://github.com/langchain4j/langchain4j-examples/blob/main/anthropic-examples/src/main/java/AnthropicToolsTest.java)""", new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                        ---
                                        sidebar_position: 2
                                        ---

                                        # Anthropic

                                        - [Anthropic Documentation](https://docs.anthropic.com/claude/docs)
                                        - [Anthropic API Reference](https://docs.anthropic.com/claude/reference)

                                        ## Maven Dependency

                                        ```xml
                                        <dependency>
                                            <groupId>dev.langchain4j</groupId>
                                            <artifactId>langchain4j-anthropic</artifactId>
                                            <version>0.35.0</version>
                                        </dependency>
                                        ```

                                        ## AnthropicChatModel

                                        ```java
                                        AnthropicChatModel model = AnthropicChatModel.builder()
                                            .apiKey(System.getenv("ANTHROPIC_API_KEY"))
                                            .modelName(CLAUDE_3_5_SONNET_20240620)
                                            .build();
                                        String answer = model.generate("Say 'Hello World'");
                                        System.out.println(answer);
                                        ```
                                        """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                        | [Amazon Bedrock](/integrations/language-models/amazon-bedrock)                   | ✅                                          | ✅                         |                                               | text                           | ✅                                         |                                                   |        |                             |
                                        | [Anthropic](/integrations/language-models/anthropic)                             | ✅                                          | ✅                         |                                               | text, image                    |                                           |                                                   | ✅      |                             |
                                        """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        )))
                        ),
                        "To implement the Anthropic tokenizer, you can follow these steps:\\n\\n1. **Add the Maven Dependency**: Include the necessary dependency in your `pom.xml` file to use the Anthropic library.\\n\\n   ```xml\\n   <dependency>\\n       <groupId>dev.langchain4j</groupId>\\n       <artifactId>langchain4j-anthropic</artifactId>\\n       <version>0.34.0</version>\\n   </dependency>\\n   ```\\n\\n2. **Create an Instance of the AnthropicChatModel**: Use the following Java code to create an instance of the `AnthropicChatModel` using your API key.\\n\\n   ```java\\n   AnthropicChatModel model = AnthropicChatModel.withApiKey(System.getenv(\\\"ANTHROPIC_API_KEY\\\"));\\n   ```\\n\\n3. **Generate Tokens**: Use the `generate` method to get a response. You can also implement a streaming response handler to handle tokens as they are generated.\\n\\n   ```java\\n   model.generate(\\\"Say 'Hello World'\\\", new StreamingResponseHandler<AiMessage>() {\\n       @Override\\n       public void onNext(String token) {\\n           // This method is called when a new token is available\\n       }\\n\\n       @Override\\n       public void onComplete(Response<AiMessage> response) {\\n           // This method is called when the model has completed responding\\n       }\\n\\n       @Override\\n       public void onError(Throwable error) {\\n           // This method is called when an error occurs\\n       }\\n   });\\n   ```\\n\\n4. **Refer to Documentation**: For further customization and options, you can refer to the [Anthropic Documentation](https://docs.anthropic.com/claude/docs) and the [API Reference](https://docs.anthropic.com/claude/reference).\\n\\nThis should help you implement the Anthropic tokenizer in your application."),
                new DatasetEntry("If I receive a 200.000 or more token size super big text, how can I handle a multicall logic to the llm so it can manage big texts? because otherwise if I send a super big text that is 200K or bigger I will get an error",
                        List.of(
                                new TextSegment("""
                                      ---
                                      sidebar_position: 5
                                      ---

                                      # Response Streaming

                                      :::note
                                      This page describes response streaming with a low-level LLM API.
                                      See [AI Services](/tutorials/ai-services#streaming) for a high-level LLM API.
                                      :::

                                      LLMs generate text one token at a time, so many LLM providers offer a way to stream the response
                                      token-by-token instead of waiting for the entire text to be generated.
                                      This significantly improves the user experience, as the user does not need to wait an unknown
                                      amount of time and can start reading the response almost immediately.

                                      For the `ChatLanguageModel` and `LanguageModel` interfaces, there are corresponding
                                      `StreamingChatLanguageModel` and `StreamingLanguageModel` interfaces.
                                      These have a similar API but can stream the responses.
                                      They accept an implementation of the `StreamingResponseHandler` interface as an argument.

                                      ```java
                                      public interface StreamingResponseHandler<T> {

                                      void onNext(String token);

                                      default void onComplete(Response<T> response) {}
                                      """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                        For example, we know that LLMs themselves are not very good at math.
                                        If your use case involves occasional math calculations, you might want to provide the LLM with a "math tool."
                                        By declaring one or multiple tools in the request to the LLM,
                                        it can then decide to call one of them if it deems it appropriate.
                                        Given a math question along with a set of math tools, the LLM might decide that to properly answer the question,
                                        it should first call one of the provided math tools.

                                        Let's see how this works in practice (with and without tools):

                                        An example of a message exchange without tools:
                                        ```
                                        Request:
                                        - messages:
                                            - UserMessage:
                                                - text: What is the square root of 475695037565?

                                        Response:
                                        - AiMessage:
                                            - text: The square root of 475695037565 is approximately 689710.
                                        ```
                                        Close, but not correct.

                                        An example of a message exchange with the following tools:
                                        ```java
                                        @Tool("Sums 2 given numbers")
                                        double sum(double a, double b) {
                                            return a + b;
                                        }
                                        """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                        #### QianfanStreamingChatModel(流式回复)
                                        LLMs generate text one token at a time, so many LLM providers offer a way to stream the response token-by-token instead of waiting for the entire text to be generated. This significantly improves the user experience, as the user does not need to wait an unknown amount of time and can start reading the response almost immediately.（因此许多LLM提供者提供了一种逐个token地传输响应的方法，而不是等待生成整个文本。这极大地改善了用户体验，因为用户不需要等待未知的时间，几乎可以立即开始阅读响应。）
                                        以下是一个通过StreamingResponseHandler来实现
                                        ```java
                                        QianfanStreamingChatModel qianfanStreamingChatModel = QianfanStreamingChatModel.builder()
                                        .apiKey("apiKey")
                                        .secretKey("secretKey")
                                        .modelName("Yi-34B-Chat")
                                        .build();
                                        qianfanStreamingChatModel.generate(userMessage, new StreamingResponseHandler<AiMessage>() {
                                        @Override
                                        public void onNext(String token) {
                                        System.out.print(token);
                                        }
                                        @Override
                                        public void onComplete(Response<AiMessage> response) {
                                        System.out.println("onComplete: " + response);
                                        }
                                        @Override
                                        public void onError(Throwable throwable) {
                                        """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                        ## Chaining multiple AI Services
                                        The more complex the logic of your LLM-powered application becomes,
                                        the more crucial it is to break it down into smaller parts, as is common practice in software development.

                                        For instance, stuffing lots of instructions into the system prompt to account for all possible scenarios
                                        is prone to errors and inefficiency. If there are too many instructions, LLMs may overlook some.
                                        Additionally, the sequence in which instructions are presented matters, making the process even more challenging.

                                        This principle also applies to tools, RAG, and model parameters such as `temperature`, `maxTokens`, etc.
                                        """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                        Your chatbot likely doesn't need to be aware of every tool you have at all times.
                                        For example, when a user simply greets the chatbot or says goodbye,
                                        it is costly and sometimes even dangerous to give the LLM access to the dozens or hundreds of tools
                                        (each tool included in the LLM call consumes a significant number of tokens)
                                        and might lead to unintended results (LLMs can hallucinate or be manipulated to invoke a tool with unintended inputs).

                                        Regarding RAG: similarly, there are times when it's necessary to provide some context to the LLM,
                                        but not always, as it incurs additional costs (more context = more tokens)
                                        and increases response times (more context = higher latency).

                                        Concerning model parameters: in certain situations, you may need LLM to be highly deterministic,
                                        so you would set a low `temperature`. In other cases, you might opt for a higher `temperature`, and so on.
                                        """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        )))

                        ),
                        "To handle a super big text of 200,000 tokens or more when working with a large language model (LLM), you can adopt a multi-call approach and utilize response streaming. Here’s how you can manage this:\\n\\n1. **Break Down the Text**: Divide the super big text into smaller manageable chunks that fit within the token limit of the LLM. This allows you to process the text without hitting the size limit.\\n\\n2. **Use Streaming Response**: Instead of waiting for the entire response, you can implement response streaming. This allows the LLM to generate text one token at a time. By using the `StreamingResponseHandler`, you can handle the tokens as they are generated, which enhances the user experience as they can start receiving information almost immediately.\\n\\n3. **Implementing Streaming**: You can set up a `StreamingResponseHandler` which will manage the incoming tokens. Each time a token is generated, the `onNext` method will be called, and you can process that token accordingly. Here's an example setup:\\n\\n   ```java\\n   QianfanStreamingChatModel qianfanStreamingChatModel = QianfanStreamingChatModel.builder()\\n       .apiKey(\\\"apiKey\\\")\\n       .secretKey(\\\"secretKey\\\")\\n       .modelName(\\\"Yi-34B-Chat\\\")\\n       .build();\\n\\n   // For each chunk of text\\n   qianfanStreamingChatModel.generate(chunk, new StreamingResponseHandler<AiMessage>() {\\n       @Override\\n       public void onNext(String token) {\\n           // Process the token (e.g., append to a result string)\\n       }\\n\\n       @Override\\n       public void onComplete(Response<AiMessage> response) {\\n           // Handle completion of the response\\n       }\\n\\n       @Override\\n       public void onError(Throwable error) {\\n           // Handle any errors\\n       }\\n   });\\n   ```\\n\\n4. **Chaining Logic**: If your application requires complex logic, ensure to break it down into smaller parts. This is crucial to avoid overloading the LLM with too many instructions at once, which may lead to inefficiencies or errors.\\n\\nBy following these steps, you can effectively manage large texts with an LLM while minimizing the risk of errors due to token size limits."),
                new DatasetEntry(
                        "give me a exemple of web socket api that stream the model output?",
                        List.of(
                                new TextSegment("""
                                        ```java
                                        ChatLanguageModel model = MistralAiChatModel.builder()
                                                        .apiKey(System.getenv("MISTRAL_AI_API_KEY")) // Please use your own Mistral AI API key
                                                        .responseFormat(MistralAiResponseFormatType.JSON_OBJECT)
                                                        .build();

                                        String userMessage = "Return JSON with two fields: transactionId and status with the values T123 and paid.";
                                        String json = model.generate(userMessage);

                                        System.out.println(json); // {"transactionId":"T123","status":"paid"}
                                        ```

                                        Streaming example:

                                        ```java
                                        StreamingChatLanguageModel streamingModel = MistralAiStreamingChatModel.builder()
                                                        .apiKey(System.getenv("MISTRAL_AI_API_KEY")) // Please use your own Mistral AI API key
                                                        .responseFormat(MistralAiResponseFormatType.JSON_OBJECT)
                                                        .build();

                                        String userMessage = "Return JSON with two fields: transactionId and status with the values T123 and paid.";

                                        CompletableFuture<Response<AiMessage>> futureResponse = new CompletableFuture<>();
                                      """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                    String userMessage = "What is the best French cheese?";
                                    String response = model.generate(userMessage);
                                    ```

                                    Streaming example:

                                    ```java
                                    StreamingChatLanguageModel streamingModel = MistralAiStreamingChatModel.builder()
                                                    .apiKey(System.getenv("MISTRAL_AI_API_KEY"))
                                                    .safePrompt(true)
                                                    .build();

                                    String userMessage = "What is the best French cheese?";

                                    CompletableFuture<Response<AiMessage>> futureResponse = new CompletableFuture<>();

                                    streamingModel.generate(userMessage, new StreamingResponseHandler() {
                                        @Override
                                        public void onNext(String token) {
                                            System.out.print(token);
                                        }

                                    @Override
                                        public void onComplete(Response<AiMessage> response) {
                                            futureResponse.complete(response);
                                        }

                                    @Override
                                        public void onError(Throwable error) {
                                            futureResponse.completeExceptionally(error);
                                        }
                                    });

                                    futureResponse.join();
                                    ```
                                    Toggling the safe prompt will prepend your messages with the following `@SystemMessage`:
                                    ```
                                    """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                      ---
                                      sidebar_position: 5
                                      ---

                                      # Response Streaming

                                      :::note
                                      This page describes response streaming with a low-level LLM API.
                                      See [AI Services](/tutorials/ai-services#streaming) for a high-level LLM API.
                                      :::

                                      LLMs generate text one token at a time, so many LLM providers offer a way to stream the response
                                      token-by-token instead of waiting for the entire text to be generated.
                                      This significantly improves the user experience, as the user does not need to wait an unknown
                                      amount of time and can start reading the response almost immediately.

                                      For the `ChatLanguageModel` and `LanguageModel` interfaces, there are corresponding
                                      `StreamingChatLanguageModel` and `StreamingLanguageModel` interfaces.
                                      These have a similar API but can stream the responses.
                                      They accept an implementation of the `StreamingResponseHandler` interface as an argument.

                                      ```java
                                      public interface StreamingResponseHandler<T> {

                                      void onNext(String token);

                                      default void onComplete(Response<T> response) {}
                                      """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                      | Parameter       | Description                                                                                                                                                                       | Type           | Example                |
                                      |-----------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------|------------------------|
                                      | `baseUrl`       | The base URL of Ollama server.                                                                                                                                                    | `String`       | http://localhost:11434 |
                                      | `modelName`     | The name of the model to use from Ollama server.                                                                                                                                  | `String`       |                        |
                                      """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                      ```java
                                      GoogleAiGeminiChatModel.builder()
                                          ...
                                          .responseFormat(ResponseFormat.builder()
                                              .type(JSON)
                                              .jsonSchema(JsonSchema.builder()...build())
                                              .build())
                                          .build();
                                      ```

                                      - For Mistral AI:
                                      ```java
                                      MistralAiChatModel.builder()
                                          ...
                                          .responseFormat(MistralAiResponseFormatType.JSON_OBJECT)
                                          .build();
                                      ```

                                      - For Ollama:
                                      ```java
                                      OllamaChatModel.builder()
                                          ...
                                          .format("json")
                                          .build();
                                      ```

                                      - For other model providers: if the underlying model provider does not support JSON mode,
                                      prompt engineering is your best bet. Also, try lowering the `temperature` for more determinism.

                                      [More examples](https://github.com/langchain4j/langchain4j-examples/blob/main/other-examples/src/main/java/OtherServiceExamples.java)

                                      ## Streaming

                                      The AI Service can [stream response](/tutorials/response-streaming) token-by-token
                                      when using the `TokenStream` return type:
                                      ```java

                                      interface Assistant {

                                      TokenStream chat(String message);
                                      }
                                      """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        )))
                        ),
                        "yes"
                ),
                new DatasetEntry(
                        "give me a example of a web socket api that stream the model output using Ktor framework",
                        List.of(
                                new TextSegment("""
                                    ```java
                                    GoogleAiGeminiChatModel.builder()
                                        ...
                                        .responseFormat(ResponseFormat.builder()
                                            .type(JSON)
                                            .jsonSchema(JsonSchema.builder()...build())
                                            .build())
                                        .build();
                                    ```

                                    - For Mistral AI:
                                    ```java
                                    MistralAiChatModel.builder()
                                        ...
                                        .responseFormat(MistralAiResponseFormatType.JSON_OBJECT)
                                        .build();
                                    ```

                                    - For Ollama:
                                    ```java
                                    OllamaChatModel.builder()
                                        ...
                                        .format("json")
                                        .build();
                                    ```

                                    - For other model providers: if the underlying model provider does not support JSON mode,
                                    prompt engineering is your best bet. Also, try lowering the `temperature` for more determinism.

                                    [More examples](https://github.com/langchain4j/langchain4j-examples/blob/main/other-examples/src/main/java/OtherServiceExamples.java)

                                    ## Streaming

                                    The AI Service can [stream response](/tutorials/response-streaming) token-by-token
                                    when using the `TokenStream` return type:
                                    ```java

                                    interface Assistant {

                                    TokenStream chat(String message);
                                    }
                                    """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                    ### Plain Java
                                    ```java
                                    OpenAiStreamingChatModel model = OpenAiStreamingChatModel.builder()
                                            .apiKey(System.getenv("OPENAI_API_KEY"))
                                            ...
                                            .build();
                                    ```
                                    """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                    ```java
                                    ChatLanguageModel model = MistralAiChatModel.builder()
                                                    .apiKey(System.getenv("MISTRAL_AI_API_KEY")) // Please use your own Mistral AI API key
                                                    .responseFormat(MistralAiResponseFormatType.JSON_OBJECT)
                                                    .build();

                                    String userMessage = "Return JSON with two fields: transactionId and status with the values T123 and paid.";
                                    String json = model.generate(userMessage);

                                    System.out.println(json); // {"transactionId":"T123","status":"paid"}
                                    ```

                                    Streaming example:

                                    ```java
                                    StreamingChatLanguageModel streamingModel = MistralAiStreamingChatModel.builder()
                                                    .apiKey(System.getenv("MISTRAL_AI_API_KEY")) // Please use your own Mistral AI API key
                                                    .responseFormat(MistralAiResponseFormatType.JSON_OBJECT)
                                                    .build();

                                    String userMessage = "Return JSON with two fields: transactionId and status with the values T123 and paid.";

                                    CompletableFuture<Response<AiMessage>> futureResponse = new CompletableFuture<>();
                                    """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                        | Provider                                                                         | [Streaming](/tutorials/response-streaming) | [Tools](/tutorials/tools) | [JSON mode](/tutorials/ai-services#json-mode) | Supported Modalities (Input)   | [Observability](/tutorials/observability) | Local                                             | Native | Comments                    |
                                         |----------------------------------------------------------------------------------|--------------------------------------------|---------------------------|-----------------------------------------------|--------------------------------|-------------------------------------------|---------------------------------------------------|--------|-----------------------------|
                                    """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                    ### Plain Java
                                    ```java
                                    StreamingChatLanguageModel model = AzureOpenAiStreamingChatModel.builder()
                                            .apiKey(System.getenv("AZURE_OPENAI_KEY"))
                                            .deploymentName("gpt-4o")
                                            .endpoint("https://langchain4j.openai.azure.com/")
                                            ...
                                            .build();
                                    ```
                                    """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        )))
                        ),
                        "yes"
                ),
                new DatasetEntry(
                        "What is langchain4j",
                        List.of(
                                new TextSegment("""
                                    ## LangChain4j Library Structure
                                    LangChain4j features a modular design, comprising:
                                    - The `langchain4j-core` module, which defines core abstractions (such as `ChatLanguageModel` and `EmbeddingStore`) and their APIs.
                                    - The main `langchain4j` module, containing useful tools like document loaders, [chat memory](/tutorials/chat-memory) implementations as well as a high-level features like [AI Services](/tutorials/ai-services).
                                    - A wide array of `langchain4j-{integration}` modules, each providing integration with various LLM providers and embedding stores into LangChain4j.
                                    You can use the `langchain4j-{integration}` modules independently. For additional features, simply import the main `langchain4j` dependency.
                                    """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                    LangChain4j began development in early 2023 amid the ChatGPT hype.
                                    We noticed a lack of Java counterparts to the numerous Python and JavaScript LLM libraries and frameworks,
                                    and we had to fix that!
                                    Although "LangChain" is in our name, the project is a fusion of ideas and concepts from LangChain, Haystack,
                                    LlamaIndex, and the broader community, spiced up with a touch of our own innovation.
                                    
                                    We actively monitor community developments, aiming to quickly incorporate new techniques and integrations,
                                    ensuring you stay up-to-date.
                                    The library is under active development. While some features are still being worked on,
                                    the core functionality is in place, allowing you to start building LLM-powered apps now!
                                    
                                    For easier integration, LangChain4j also includes integration with
                                    [Quarkus](/tutorials/quarkus-integration) and [Spring Boot](/tutorials/spring-boot-integration).
                                    """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                    ---
                                    sidebar_position: 1
                                    title: Introduction
                                    ---
                                    
                                    # Introduction
                                    
                                    Welcome!
                                    
                                    The goal of LangChain4j is to simplify integrating LLMs into Java applications.
                                    """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                    ## 2 levels of abstraction
                                    LangChain4j operates on two levels of abstraction:
                                    - Low level. At this level, you have the most freedom and access to all the low-level components such as
                                    [ChatLanguageModel](/tutorials/chat-and-language-models), `UserMessage`, `AiMessage`, `EmbeddingStore`, `Embedding`, etc.
                                    These are the "primitives" of your LLM-powered application.
                                    You have complete control over how to combine them, but you will need to write more glue code.
                                    - High level. At this level, you interact with LLMs using high-level APIs like [AI Services](/tutorials/ai-services),
                                    which hides all the complexity and boilerplate from you.
                                    You still have the flexibility to adjust and fine-tune the behavior, but it is done in a declarative manner.
                                    
                                    [![](/img/langchain4j-components.png)](/intro)
                                    """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                    System.out.println("Question: " + userQuery); // What is your favourite sport?
                                            System.out.println("Response: " + embeddingMatch.embedded().text()); // I like football.
                                        }
                                    }
                                    ```
                                    For this example, we'll add 2 text segments, but LangChain4j offers built-in support for loading documents from various sources:
                                    File System, URL, Amazon S3, Azure Blob Storage, GitHub, Tencent COS.
                                    Additionally, LangChain4j supports parsing multiple document types:
                                    text, pdf, doc, xls, ppt.
                                    
                                    The output will be similar to this:
                                    
                                    ```plaintext
                                    Question: What is your favourite sport?
                                    Response: I like football.
                                    ```
                                    
                                    Of course, you can combine MistralAI Embeddings with RAG (Retrieval-Augmented Generation) techniques.
                                    
                                    In [RAG](/tutorials/rag) you will learn how to use RAG techniques for ingestion, retrieval and Advanced Retrieval with LangChain4j.
                                    """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        )))
                        ),
                        "yes"
                ),
                new DatasetEntry(
                        "Does it support ollama?",
                        List.of(
                                new TextSegment("""
                                   ---
                                   sidebar_position: 14
                                   ---
    
                                   # Ollama
    
                                   ### What is Ollama?
    
                                   Ollama is an advanced AI tool that allows users to easily set up and run large language models
                                   locally (in CPU and GPU modes). With Ollama, users can leverage powerful language models such as
                                   Llama 2 and even customize and create their own models. Ollama bundles model weights, configuration,
                                   and data into a single package, defined by a Modelfile. It optimizes setup and configuration
                                   details, including GPU usage.
    
                                   For more details about Ollama, check these out:
    
                                   - https://ollama.ai/
                                   - https://github.com/jmorganca/ollama
    
                                   ### Talks
    
                                   Watch this presentation at [Docker Con 23](https://www.dockercon.com/2023/program):
                                   """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                   import java.util.concurrent.CompletableFuture;
                                   
                                   public class OllamaStreamingChatExample {

                                   static String MODEL_NAME = "orca-mini"; // try "mistral", "llama2", "codellama" or "phi"
                                     static String DOCKER_IMAGE_NAME = "langchain4j/ollama-" + MODEL_NAME + ":latest";

                                   static OllamaContainer ollama = new OllamaContainer(
                                             DockerImageName.parse(DOCKER_IMAGE_NAME).asCompatibleSubstituteFor("ollama/ollama"));

                                   public static void main(String[] args) {
                                       ollama.start();
                                       StreamingChatLanguageModel model = OllamaStreamingChatModel.builder()
                                           .baseUrl(String.format("http://%s:%d", ollama.getHost(), ollama.getMappedPort(PORT)))
                                           .modelName(MODEL_NAME)
                                           .temperature(0.0)
                                           .build();

                                   String userMessage = "Write a 100-word poem about Java and AI";

                                   CompletableFuture<Response<AiMessage>> futureResponse = new CompletableFuture<>();
                                       model.generate(userMessage, new StreamingResponseHandler<AiMessage>() {
                                   """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                    ---
                                    sidebar_position: 13
                                    ---

                                    # Ollama

                                    https://ollama.com/

                                    ## Maven Dependency

                                    ```xml
                                    <dependency>
                                        <groupId>dev.langchain4j</groupId>
                                        <artifactId>langchain4j-ollama</artifactId>
                                        <version>0.35.0</version>
                                    </dependency>
                                    ```

                                    ## APIs

                                    - `OllamaEmbeddingModel`

                                    ## Examples

                                    - [OllamaEmbeddingModelIT](https://github.com/langchain4j/langchain4j/blob/main/langchain4j-ollama/src/test/java/dev/langchain4j/model/ollama/OllamaEmbeddingModelIT.java)
                                    """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                   | [Mistral AI](/integrations/language-models/mistral-ai)                           | ✅                                          | ✅                         | ✅                                             | text                           |                                           |                                                   |        |                             |
                                   | [Ollama](/integrations/language-models/ollama)                                   | ✅                                          | ✅                         | ✅                                             | text, image                    | ✅                                         | ✅                                                 |        |                             |
                                   """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                    @Override
                                          public void onNext(String token) {
                                            System.out.print(token);
                                          }

                                    @Override
                                          public void onComplete(Response<AiMessage> response) {
                                            futureResponse.complete(response);
                                          }

                                    @Override
                                          public void onError(Throwable error) {
                                            futureResponse.completeExceptionally(error);
                                          }
                                        });

                                    futureResponse.join();
                                        ollama.stop();
                                      }
                                    }
                                    ```

                                    ### Parameters

                                    `OllamaChatModel` and `OllamaStreamingChatModel` classes can be instantiated with the following
                                    params with the builder pattern:
                                    """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        )))
                        ),
                        "yes"
                ),
                new DatasetEntry(
                        "what maven dependency do I need for ollama?",
                        List.of(
                                new TextSegment("""
                                    ---
                                    sidebar_position: 13
                                    ---
    
                                    # Ollama
    
                                    https://ollama.com/
    
                                    ## Maven Dependency
    
                                    ```xml
                                    <dependency>
                                        <groupId>dev.langchain4j</groupId>
                                        <artifactId>langchain4j-ollama</artifactId>
                                        <version>0.35.0</version>
                                    </dependency>
                                    ```
    
                                    ## APIs
    
                                    - `OllamaEmbeddingModel`
    
                                    ## Examples
    
                                    - [OllamaEmbeddingModelIT](https://github.com/langchain4j/langchain4j/blob/main/langchain4j-ollama/src/test/java/dev/langchain4j/model/ollama/OllamaEmbeddingModelIT.java)
                                    """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                    ---
                                    sidebar_position: 11
                                    ---
    
                                    # Jlama
                                    [Jlama Project](https://github.com/tjake/Jlama)
    
                                    ## Project setup
    
                                    To install langchain4j to your project, add the following dependency:
    
                                    For Maven project `pom.xml`
    
                                    ```xml
    
                                    <dependency>
                                        <groupId>dev.langchain4j</groupId>
                                        <artifactId>langchain4j</artifactId>
                                        <version>0.35.0</version>
                                    </dependency>
    
                                    <dependency>
                                        <groupId>dev.langchain4j</groupId>
                                        <artifactId>langchain4j-jlama</artifactId>
                                        <version>0.35.0</version>
                                    </dependency>
    
                                    <dependency>
                                        <groupId>com.github.tjake</groupId>
                                        <artifactId>jlama-native</artifactId>
                                        <!-- for faster inference. supports linux-x86_64, macos-x86_64/aarch_64, windows-x86_64\s
                                           Use https://github.com/trustin/os-maven-plugin to detect os and arch -->
                                        <classifier>${os.detected.name}-${os.detected.arch}</classifier>
                                        <version>${jlama.version}</version> <!-- Version from langchain4j-jlama pom -->
                                    </dependency>
    
                                    ```
    
                                    For Gradle project `build.gradle`
                                    """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                    ---
                                    sidebar_position: 9
                                    ---
    
                                    # Jlama
                                    [Jlama Project](https://github.com/tjake/Jlama)
    
                                    ### Project setup
    
                                    To install langchain4j to your project, add the following dependency:
    
                                    For Maven project `pom.xml`
    
                                    ```xml
    
                                    <dependency>
                                        <groupId>dev.langchain4j</groupId>
                                        <artifactId>langchain4j</artifactId>
                                        <version>0.35.0</version>
                                    </dependency>
    
                                    <dependency>
                                        <groupId>dev.langchain4j</groupId>
                                        <artifactId>langchain4j-jlama</artifactId>
                                        <version>0.35.0</version>
                                    </dependency>
    
                                    <dependency>
                                        <groupId>com.github.tjake</groupId>
                                        <artifactId>jlama-native</artifactId>
                                        <!-- for faster inference. supports linux-x86_64, macos-x86_64/aarch_64, windows-x86_64\s
                                            Use https://github.com/trustin/os-maven-plugin to detect os and arch -->
                                        <classifier>${os.detected.name}-${os.detected.arch}</classifier>
                                        <version>${jlama.version}</version> <!-- Version from langchain4j-jlama pom -->
                                    </dependency>
                                    ```
    
                                    For Gradle project `build.gradle`
                                    """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                    <dependency>
                                        <groupId>org.testcontainers</groupId>
                                        <artifactId>testcontainers</artifactId>
                                        <version>1.19.1</version>
                                    </dependency>
                                    ```
    
                                    Try out a simple chat example code:
    
                                    ```java
                                    import dev.langchain4j.model.chat.ChatLanguageModel;
                                    import dev.langchain4j.model.ollama.OllamaChatModel;
                                    import org.testcontainers.ollama.OllamaContainer;
                                    import org.testcontainers.utility.DockerImageName;
    
                                    public class OllamaChatExample {
    
                                    public static void main(String[] args) {
                                        // The model name to use (e.g., "orca-mini", "mistral", "llama2", "codellama", "phi", or
                                        // "tinyllama")
                                        String modelName = "orca-mini";
    
                                    // Create and start the Ollama container
                                        OllamaContainer ollama =
                                            new OllamaContainer(DockerImageName.parse("langchain4j/ollama-" + modelName + ":latest")
                                                .asCompatibleSubstituteFor("ollama/ollama"));
                                        ollama.start();
                                    """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                    ---
                                    sidebar_position: 14
                                    ---
    
                                    # Ollama
    
                                    ### What is Ollama?
    
                                    Ollama is an advanced AI tool that allows users to easily set up and run large language models
                                    locally (in CPU and GPU modes). With Ollama, users can leverage powerful language models such as
                                    Llama 2 and even customize and create their own models. Ollama bundles model weights, configuration,
                                    and data into a single package, defined by a Modelfile. It optimizes setup and configuration
                                    details, including GPU usage.
    
                                    For more details about Ollama, check these out:
    
                                    - https://ollama.ai/
                                    - https://github.com/jmorganca/ollama
    
                                    ### Talks
    
                                    Watch this presentation at [Docker Con 23](https://www.dockercon.com/2023/program):
                                    """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        )))
                        ),
                        "yes"
                ),
                new DatasetEntry(
                        "I wanna log request sent to model using Langchain4j. how can I do that?",
                        List.of(
                                new TextSegment("""
                                   ---
                                   sidebar_position: 30
                                   ---

                                   # Logging

                                   LangChain4j uses [SLF4J](https://www.slf4j.org/) for logging,
                                   allowing you to plug in any logging backend you prefer,
                                   such as [Logback](https://logback.qos.ch/) or [Log4j](https://logging.apache.org/log4j/2.x/index.html)).

                                   ## Pure Java

                                   You can enable logging of each request and response to the LLM by setting
                                   `.logRequests(true)` and `.logResponses(true)` when creating an instance of the model:
                                   ```java
                                   OpenAiChatModel.builder()
                                       ...
                                       .logRequests(true)
                                       .logResponses(true)
                                       .build();
                                   ```

                                   Make sure you have one of the SLF4J logging backends in your dependencies, for example, Logback:
                                   ```xml
                                   <dependency>
                                       <groupId>ch.qos.logback</groupId>
                                       <artifactId>logback-classic</artifactId>
                                       <version>1.5.8</version>
                                   </dependency>
                                   ```

                                   ## Quarkus

                                   When using [Quarkus integration](/tutorials/quarkus-integration),
                                   logging is configured in the `application.properties` file:
                                    """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                   @GetMapping("/chat")
                                       public String model(@RequestParam(value = "message", defaultValue = "Hello") String message) {
                                           return chatLanguageModel.generate(message);
                                       }
                                   }
                                   ```

                                   If you need an instance of a `StreamingChatLanguageModel`,
                                   use the `streaming-chat-model` instead of the `chat-model` properties:
                                   ```
                                   langchain4j.open-ai.streaming-chat-model.api-key=${OPENAI_API_KEY}
                                   ...
                                   ```

                                   ## Spring Boot starter for declarative AI Services

                                   LangChain4j provides a Spring Boot starter for auto-configuring
                                   [AI Services](/tutorials/ai-services), [RAG](/tutorials/rag), [Tools](/tutorials/tools) etc.

                                   Assuming you have already imported one of the integrations starters (see above),
                                   import `langchain4j-spring-boot-starter`:
                                   ```xml
                                   <dependency>
                                       <groupId>dev.langchain4j</groupId>
                                       <artifactId>langchain4j-spring-boot-starter</artifactId>
                                       <version>0.35.0</version>
                                   </dependency>
                                   ```
                                   """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                   For example, for OpenAI (`langchain4j-open-ai`), the dependency name would be `langchain4j-open-ai-spring-boot-starter`:
                                   ```xml
                                   <dependency>
                                       <groupId>dev.langchain4j</groupId>
                                       <artifactId>langchain4j-open-ai-spring-boot-starter</artifactId>
                                       <version>0.35.0</version>
                                   </dependency>
                                   ```

                                   Then, you can configure model parameters in the `application.properties` file as follows:
                                   ```
                                   langchain4j.open-ai.chat-model.api-key=${OPENAI_API_KEY}
                                   langchain4j.open-ai.chat-model.model-name=gpt-4o
                                   langchain4j.open-ai.chat-model.log-requests=true
                                   langchain4j.open-ai.chat-model.log-responses=true
                                   ...
                                   ```

                                   In this case, an instance of `OpenAiChatModel` (an implementation of a `ChatLanguageModel`) will be automatically created,
                                   and you can autowire it where needed:
                                   ```java
                                   @RestController
                                   public class ChatController {

                                   ChatLanguageModel chatLanguageModel;

                                   public ChatController(ChatLanguageModel chatLanguageModel) {
                                           this.chatLanguageModel = chatLanguageModel;
                                       }
                                   """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                    ### Spring Boot
                                    Add to the `application.properties`:
                                    ```properties
                                    langchain4j.open-ai.chat-model.api-key=${OPENAI_API_KEY}
                                    langchain4j.open-ai.chat-model.base-url=...
                                    langchain4j.open-ai.chat-model.custom-headers=...
                                    langchain4j.open-ai.chat-model.frequency-penalty=...
                                    langchain4j.open-ai.chat-model.log-requests=...
                                    langchain4j.open-ai.chat-model.log-responses=...
                                    langchain4j.open-ai.chat-model.logit-bias=...
                                    langchain4j.open-ai.chat-model.max-retries=...
                                    langchain4j.open-ai.chat-model.max-completion-tokens=...
                                    langchain4j.open-ai.chat-model.max-tokens=...
                                    langchain4j.open-ai.chat-model.model-name=...
                                    langchain4j.open-ai.chat-model.organization-id=...
                                    langchain4j.open-ai.chat-model.parallel-tool-calls=...
                                    langchain4j.open-ai.chat-model.presence-penalty=...
                                    langchain4j.open-ai.chat-model.proxy.host=...
                                    langchain4j.open-ai.chat-model.proxy.port=...
                                    langchain4j.open-ai.chat-model.proxy.type=...
                                    langchain4j.open-ai.chat-model.response-format=...
                                    langchain4j.open-ai.chat-model.seed=...
                                    """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                    ### Spring Boot
                                    Add to the `application.properties`:
                                    ```properties
                                    langchain4j.open-ai.embedding-model.api-key=${OPENAI_API_KEY}
                                    langchain4j.open-ai.embedding-model.base-url=...
                                    langchain4j.open-ai.embedding-model.custom-headers=...
                                    langchain4j.open-ai.embedding-model.dimensions=...
                                    langchain4j.open-ai.embedding-model.log-requests=...
                                    langchain4j.open-ai.embedding-model.log-responses=...
                                    langchain4j.open-ai.embedding-model.max-retries=...
                                    langchain4j.open-ai.embedding-model.model-name=...
                                    langchain4j.open-ai.embedding-model.organization-id=...
                                    langchain4j.open-ai.embedding-model.proxy.host=...
                                    langchain4j.open-ai.embedding-model.proxy.port=...
                                    langchain4j.open-ai.embedding-model.proxy.type=...
                                    langchain4j.open-ai.embedding-model.timeout=...
                                    langchain4j.open-ai.embedding-model.user=...
                                    ```
                                    """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        )))
                        ),
                        "yes"
                ),
                new DatasetEntry(
                        "what log level I have to set?",
                        List.of(
                                new TextSegment("""
                                    ```properties
                                    ...
                                    quarkus.langchain4j.openai.chat-model.log-requests = true
                                    quarkus.langchain4j.openai.chat-model.log-responses = true
                                    quarkus.log.console.enable = true
                                    quarkus.log.file.enable = false
                                    ```
                                    
                                    These properties can also be set and changed in the Quarkus Dev UI,
                                    when running the application in dev mode (`mvn quarkus:dev`).
                                    The Dev UI is then available at `http://localhost:8080/q/dev-ui`.
                                    
                                    ## Spring Boot
                                    
                                    When using [Spring Boot integration](/tutorials/spring-boot-integration),
                                    logging is configured in the `application.properties` file:
                                    
                                    ```properties
                                    ...
                                    langchain4j.open-ai.chat-model.log-requests = true
                                    langchain4j.open-ai.chat-model.log-responses = true
                                    logging.level.dev.langchain4j = DEBUG
                                    logging.level.dev.ai4j.openai4j = DEBUG
                                    ```
                                    """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                    ---
                                    sidebar_position: 30
                                    ---
                                    
                                    # Logging
                                    
                                    LangChain4j uses [SLF4J](https://www.slf4j.org/) for logging,
                                    allowing you to plug in any logging backend you prefer,
                                    such as [Logback](https://logback.qos.ch/) or [Log4j](https://logging.apache.org/log4j/2.x/index.html)).
                                    
                                    ## Pure Java
                                    
                                    You can enable logging of each request and response to the LLM by setting
                                    `.logRequests(true)` and `.logResponses(true)` when creating an instance of the model:
                                    ```java
                                    OpenAiChatModel.builder()
                                        ...
                                        .logRequests(true)
                                        .logResponses(true)
                                        .build();
                                    ```
                                    
                                    Make sure you have one of the SLF4J logging backends in your dependencies, for example, Logback:
                                    ```xml
                                    <dependency>
                                        <groupId>ch.qos.logback</groupId>
                                        <artifactId>logback-classic</artifactId>
                                        <version>1.5.8</version>
                                    </dependency>
                                    ```
                                    
                                    ## Quarkus
                                    
                                    When using [Quarkus integration](/tutorials/quarkus-integration),
                                    logging is configured in the `application.properties` file:
                                    """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                    Defaults for all models can be found on the pages of the respective providers under [Integrations](/integrations).
                                    
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
                                    
                                    ## Setting Parameters in Quarkus
                                    LangChain4j parameters in Quarkus applications can be set in the `application.properties` file as follows:
                                    ```
                                    quarkus.langchain4j.openai.api-key=${OPENAI_API_KEY}
                                    quarkus.langchain4j.openai.chat-model.temperature=0.5
                                    quarkus.langchain4j.openai.timeout=60s
                                    ```
                                    """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                    ### Spring Boot
                                    Add to the `application.properties`:
                                    ```properties
                                    langchain4j.open-ai.embedding-model.api-key=${OPENAI_API_KEY}
                                    langchain4j.open-ai.embedding-model.base-url=...
                                    langchain4j.open-ai.embedding-model.custom-headers=...
                                    langchain4j.open-ai.embedding-model.dimensions=...
                                    langchain4j.open-ai.embedding-model.log-requests=...
                                    langchain4j.open-ai.embedding-model.log-responses=...
                                    langchain4j.open-ai.embedding-model.max-retries=...
                                    langchain4j.open-ai.embedding-model.model-name=...
                                    langchain4j.open-ai.embedding-model.organization-id=...
                                    langchain4j.open-ai.embedding-model.proxy.host=...
                                    langchain4j.open-ai.embedding-model.proxy.port=...
                                    langchain4j.open-ai.embedding-model.proxy.type=...
                                    langchain4j.open-ai.embedding-model.timeout=...
                                    langchain4j.open-ai.embedding-model.user=...
                                    ```
                                    """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        ))),
                                new TextSegment("""
                                    Your chatbot likely doesn't need to be aware of every tool you have at all times.
                                    For example, when a user simply greets the chatbot or says goodbye,
                                    it is costly and sometimes even dangerous to give the LLM access to the dozens or hundreds of tools
                                    (each tool included in the LLM call consumes a significant number of tokens)
                                    and might lead to unintended results (LLMs can hallucinate or be manipulated to invoke a tool with unintended inputs).
                                    
                                    Regarding RAG: similarly, there are times when it's necessary to provide some context to the LLM,
                                    but not always, as it incurs additional costs (more context = more tokens)
                                    and increases response times (more context = higher latency).
                                    
                                    Concerning model parameters: in certain situations, you may need LLM to be highly deterministic,
                                    so you would set a low `temperature`. In other cases, you might opt for a higher `temperature`, and so on.
                                    
                                    Metadata: Metadata { metadata = {absolute_directory_path=/home/dkafetzis/Documents/evals/../langchain4j/docs/docs/tutorials, index=27, file_name=5-ai-services.md} }
                                    -------------------------------------------------------
                                    Text:\s
                                    {
                                      "label": "Code Execution Engines",
                                      "position": 15,
                                      "link": {
                                        "type": "generated-index",
                                        "description": "Code Execution Engines"
                                      }
                                    }
                                    """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        )))
                        ),
                        "yes"
                )
        );
    }
}
