package dev.langchain4j.evals;

import java.util.List;
import java.util.Map;

class Dataset {

    static List<DatasetEntry> get() {
        return List.of(
                new DatasetEntry(
                        "give me a exemple of web socket api that stream the model output?",
                        Map.of(
                                "\\tutorials\\4-response-streaming.md", 0.5,
                                "\\tutorials\\5-ai-services.md", 0.5
                        )
                ),
                new DatasetEntry(
                        "give me a example of a web socket api that stream the model output using Ktor framework",
                        Map.of(
                                "\\tutorials\\4-response-streaming.md", 0.5,
                                "\\tutorials\\5-ai-services.md", 0.5
                        )
                ),
                new DatasetEntry(
                        "What LLMs does langchain4j support?",
                        Map.of(
                                "\\integrations\\language-models\\index.md", 1.0
                        )
                ),
                new DatasetEntry(
                        "Does it support ollama?",
                        Map.of(
                                "\\integrations\\language-models\\index.md", 1.0,
                                "\\integrations\\language-models\\ollama.md", 1.0
                        )
                ),
                new DatasetEntry(
                        "what maven dependency do I need for ollama?",
                        Map.of(
                                "\\integrations\\language-models\\ollama.md", 1.0
                        )
                ),
                new DatasetEntry(
                        "I wanna log request sent to model using Langchain4j. how can I do?",
                        Map.of(
                                "\\tutorials\\logging.md", 1.0
                        )
                ),
                new DatasetEntry(
                        "what log level I have to set?",
                        Map.of(
                                "\\tutorials\\logging.md", 1.0
                        )
                ),
                new DatasetEntry(
                        "How do I add OpenAi to my spring boot project?",
                        Map.of(
                                "\\tutorials\\spring-boot-integration.md", 1.0,
                                "\\integrations\\language-models\\open-ai.md", 1.0
                        )
                ),
                new DatasetEntry(
                        "How to use LangChain4j with Quarkus?",
                        Map.of(
                                "\\tutorials\\quarkus-integration.md", 1.0,
                                "\\integrations\\frameworks\\quarkus.md", 1.0
                        )
                ),
                new DatasetEntry(
                        "how do I create a chat with Gemini?",
                        Map.of(
                                "\\integrations\\language-models\\google-ai-gemini.md", 1.0,
                                "\\integrations\\language-models\\google-vertex-ai-gemini.md", 1.0
                        )
                ),
                new DatasetEntry(
                        "Can I send a file using langchain ?",
                        Map.of(
                                "\\tutorials\\7-rag.md", 1.0
                        )
                ),
                new DatasetEntry(
                        "how do i configure ollama?",
                        Map.of(
                                "\\integrations\\language-models\\ollama.md", 1.0
                        )
                ),
                new DatasetEntry(
                        "What maven dependency does ollama need?",
                        Map.of(
                                "\\integrations\\language-models\\ollama.md", 1.0
                        )
                ),
                new DatasetEntry(
                        "What Maven dependencies do I need for Open AI and spring boot?",
                        Map.of(
                                "\\tutorials\\spring-boot-integration.md", 1.0,
                                "\\integrations\\language-models\\open-ai.md", 1.0
                        )
                ),
                new DatasetEntry(
                        "What is langchain4j",
                        Map.of(
                                "\\intro.md", 1.0
                        )
                ),
                new DatasetEntry(
                        "How to customize ChatMemoryStore",
                        Map.of(
                                "\\tutorials\\2-chat-memory.md", 1.0
                        )
                ),
                new DatasetEntry(
                        "What does List<ChatMessage> messages contain in the updateMessages function?",
                        Map.of(
                                "\\tutorials\\2-chat-memory.md", 1.0
                        )
                ),
                new DatasetEntry(
                        "For this ChatMemoryStore interface, should I return the message of the user's question, or the message containing the systemmessage, prompt, or assistant's answer? Which messages should I return only to be enough for LLM to understand the context?",
                        Map.of(
                                "\\tutorials\\2-chat-memory.md", 1.0
                        )
                ),
                new DatasetEntry(
                        "Is the content of SystemMessage sent for every request? What is the difference between this and directly using prompt + user message to send to gpt?",
                        Map.of(
                                "\\tutorials\\2-chat-memory.md", 1.0
                        )
                ),
                new DatasetEntry(
                        "Don't you mean that SystemMessage will also be sent every time a request is made, and SystemMessage also consumes tokens?",
                        Map.of(
                                "\\tutorials\\2-chat-memory.md", 1.0
                        )
                ),
                new DatasetEntry(
                        "I'm calculating embeddings using local models like AllMiniLmL6V2EmbeddingModel. Is there a way to leverage GPU?",
                        Map.of(
                                "\\integrations\\embedding-models\\1-in-process.md", 1.0
                        )
                ),
                new DatasetEntry(
                        "how to compute embeddings using GPU instead of CPU",
                        Map.of(
                                "\\integrations\\embedding-models\\1-in-process.md", 1.0
                        )
                ),
                new DatasetEntry(
                        "Why can't I see any logs in the console after I configured it?",
                        Map.of(
                                "\\tutorials\\logging.md", 1.0
                        )
                ),
                new DatasetEntry(
                        "How can I do embedding rag?",
                        Map.of(
                                "\\tutorials\\7-rag.md", 1.0
                        )
                ),
                new DatasetEntry(
                        "How can I make an LLM configuration to return always a JSON object as a response?",
                        Map.of(
                                "\\tutorials\\5-ai-services.md", 1.0
                        )
                ),
                new DatasetEntry(
                        "Which property is the one that sets a model to return always a json?",
                        Map.of(
                                "\\tutorials\\5-ai-services.md", 1.0
                        )
                ),
                new DatasetEntry(
                        "Hi, is there a way to use langsmith together with langchain4j? Or some other way to get insight into what is happening with your chains?",
                        Map.of(
                                "\\tutorials\\observability.md", 1.0
                        )
                ),
                new DatasetEntry(
                        "Is langchain4j unable to load online documents?",
                        Map.of(
                                "\\tutorials\\7-rag.md", 1.0
                        )
                ),
                new DatasetEntry(
                        "What are AllMiniLmL6V2EmbeddingModel and BgeSmallEnV15QuantizedEmbeddingModel in langchain4j",
                        Map.of(
                                "\\integrations\\embedding-models\\1-in-process.md", 1.0
                        )
                ),
                new DatasetEntry(
                        "In Langchain4j, I have a prompt text. Should I put it in UserMessage or SystemMessage? Is there any difference between the two?",
                        Map.of(
                                "\\tutorials\\1-chat-and-language-models.md", 1.0
                        )
                ),
                new DatasetEntry(
                        "What is the logic of chatMemory in langchain4j? .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(5)); How is it stored? What does the 5 mean?",
                        Map.of(
                                "\\tutorials\\2-chat-memory.md", 1.0
                        )
                ),
                new DatasetEntry(
                        "In langchain4j, AiServices<Agent> aiServices = AiServices.builder(Agent.class)\n" +
                                ".streamingChatLanguageModel(model)\n" +
                                ".chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(20));\n" +
                                "What does the memoryId corresponding to this chatMemoryProvider represent? Why is there no place to configure memoryId?",
                        Map.of(
                                "\\tutorials\\2-chat-memory.md", 0.5,
                                "\\tutorials\\5-ai-services.md", 0.5
                        )
                ),
                new DatasetEntry(
                        "Why is the memoryId obtained from InMemoryChatMemoryStore still default after I specify the memoryId?",
                        Map.of(
                                "\\tutorials\\2-chat-memory.md", 0.5,
                                "\\tutorials\\5-ai-services.md", 0.5
                        )
                ),
                new DatasetEntry(
                        "How to get the document metadata retrieved by embedding in langchain4j",
                        Map.of(
                                "\\tutorials\\7-rag.md", 1.0
                        )
                ),
                new DatasetEntry(
                        "Hello, can you create me a code example of a chat agent, who can make groups of a list? using tool calls",
                        Map.of(
                                "\\tutorials\\5-ai-services.md", 1.0
                        )
                )
        );
    }
}
