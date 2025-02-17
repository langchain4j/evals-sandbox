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
                                # Quarkus Integration
                                
                                [Quarkus](https://quarkus.io/) provides a superb [extension for LangChain4j](https://github.com/quarkiverse/quarkus-langchain4j).
                                
                                You can find all the necessary documentation [here](https://docs.quarkiverse.io/quarkus-langchain4j/dev/index.html).
                                """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/tutorials",
                                                "file_name", "quarkus-integration.md"
                                        ))),
                                TextSegment.from("""
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
                                
                                - AI Services page: provides a table of all AI Services detected in the application along with a list of tools that they are declared to use.
                                - Embeddings store access: Allows embeddings to be added to the embeddings store and searched.
                                - Tools page: provides a list of tools detected in the application.
                                - Chat page: allows you to manually hold a conversation with a chat model. This page is only available if the application contains a chat model.
                                - Images page: allows you to test the outputs of image models and tune its parameters (for models which support it).
                                - Moderation page: allows you to test the outputs of moderation models - you submit a prompt and receive a list of scores for each appropriateness category (for models which support it).
                                
                                
                                For more detailed explanation of the extension features, see the [Quarkus documentation](https://docs.quarkiverse.io/quarkus-langchain4j/dev/) for the langchain4j extension.
                                """, new Metadata(
                                        Map.of(
                                                "absolute_directory_path", "evals/../langchain4j/docs/docs/integrations/frameworks",
                                                "file_name", "quarkus.md"
                                        )
                                ))
                        )
                ),
                new DatasetEntry(
                        "What LLMs does langchain4j support?",
                        List.of(
                                new TextSegment(
                                        """
                                                | Provider                                                                         | [Streaming](/tutorials/response-streaming) | [Tools](/tutorials/tools) | [JSON mode](/tutorials/ai-services#json-mode) | Supported Modalities (Input)   | [Observability](/tutorials/observability) | Local                                             | Native | Comments                    |
                                                |----------------------------------------------------------------------------------|--------------------------------------------|---------------------------|-----------------------------------------------|--------------------------------|-------------------------------------------|---------------------------------------------------|--------|-----------------------------|
                                                | [Amazon Bedrock](/integrations/language-models/amazon-bedrock)                   | ✅                                          | ✅                         |                                               | text                           | ✅                                         |                                                   |        |                             |
                                                | [Anthropic](/integrations/language-models/anthropic)                             | ✅                                          | ✅                         |                                               | text, image                    |                                           |                                                   | ✅      |                             |
                                                | [Azure OpenAI](/integrations/language-models/azure-open-ai)                      | ✅                                          | ✅                         | ✅                                             | text, image                    | ✅                                         |                                                   |        |                             |
                                                | [ChatGLM](/integrations/language-models/chatglm)                                 |                                            |                           |                                               | text                           |                                           |                                                   |        |                             |
                                                | [DashScope](/integrations/language-models/dashscope)                             | ✅                                          | ✅                         |                                               | text, image, audio             | ✅                                         |                                                   |        |                             |
                                                | [GitHub Models](/integrations/language-models/github-models)                     | ✅                                          | ✅                         | ✅                                             | text                           | ✅                                         |                                                   |        |                             |
                                                | [Google AI Gemini](/integrations/language-models/google-ai-gemini)               | ✅                                          | ✅                         | ✅                                             | text, image, audio, video, PDF | ✅                                         |                                                   |        |                             |
                                                | [Google Vertex AI Gemini](/integrations/language-models/google-vertex-ai-gemini) | ✅                                          | ✅                         | ✅                                             | text, image, audio, video, PDF | ✅                                         |                                                   |        |                             |
                                                | [Google Vertex AI PaLM 2](/integrations/language-models/google-palm)             |                                            |                           |                                               | text                           |                                           |                                                   | ✅      |                             |
                                                | [Hugging Face](/integrations/language-models/hugging-face)                       |                                            |                           |                                               | text                           |                                           |                                                   |        |                             |
                                                | [Jlama](/integrations/language-models/jlama)                                     | ✅                                          | ✅                         |                                               | text                           |                                           | ✅                                                 | ✅      |                             |
                                                | [LocalAI](/integrations/language-models/local-ai)                                | ✅                                          | ✅                         |                                               | text                           |                                           | ✅                                                 |        |                             |
                                                | [Mistral AI](/integrations/language-models/mistral-ai)                           | ✅                                          | ✅                         | ✅                                             | text                           |                                           |                                                   |        |                             |
                                                | [Ollama](/integrations/language-models/ollama)                                   | ✅                                          | ✅                         | ✅                                             | text, image                    | ✅                                         | ✅                                                 |        |                             |
                                                | [OpenAI](/integrations/language-models/open-ai)                                  | ✅                                          | ✅                         | ✅                                             | text, image                    | ✅                                         | Compatible with: Ollama, LM Studio, GPT4All, etc. | ✅      | Compatible with: Groq, etc. |
                                                | [Qianfan](/integrations/language-models/qianfan)                                 | ✅                                          | ✅                         |                                               | text                           |                                           |                                                   |        |                             |
                                                | [Cloudflare Workers AI](/integrations/language-models/workers-ai)                |                                            |                           |                                               | text                           |                                           |                                                   |        |                             |
                                                | [Zhipu AI](/integrations/language-models/zhipu-ai)                               | ✅                                          | ✅                         |                                               | text, image                    | ✅                                         |                                                   |        |                             |
                                                """
                                        , new Metadata(Map.of(
                                                "absolute_directory_path","evals/../langchain4j/docs/docs/integrations/language-models",
                                                "file_name","index.md"
                                ))
                                )
                        )
                )
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
