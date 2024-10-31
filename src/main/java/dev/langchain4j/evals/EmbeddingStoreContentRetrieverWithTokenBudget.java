package dev.langchain4j.evals;

import dev.langchain4j.data.document.Metadata;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.Content;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.query.Query;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

/**
 * Retrieves the most similar  from the {@link EmbeddingStore} that fit into the specified token budget.
 * Each {@link Content} is supposed to have a "whole_document_token_count" {@link Metadata} entry.
 * Text of the returned {@link Content}s does not really matter,
 * what matters is unique "relative_path" {@link Metadata} entries.
 */
public class EmbeddingStoreContentRetrieverWithTokenBudget implements ContentRetriever {

    private final EmbeddingStore<TextSegment> embeddingStore;
    private final EmbeddingModel embeddingModel;
    private final int tokenBudget;

    public EmbeddingStoreContentRetrieverWithTokenBudget(EmbeddingStore<TextSegment> embeddingStore,
                                                         EmbeddingModel embeddingModel,
                                                         int tokenBudget) {
        this.embeddingStore = embeddingStore;
        this.embeddingModel = embeddingModel;
        this.tokenBudget = tokenBudget;
    }

    @Override
    public List<Content> retrieve(Query query) {

        Embedding queryEmbedding = embeddingModel.embed(query.text()).content();

        EmbeddingSearchRequest searchRequest = EmbeddingSearchRequest.builder()
                .queryEmbedding(queryEmbedding)
                .maxResults(Integer.MAX_VALUE)
                .build();

        EmbeddingSearchResult<TextSegment> searchResult = embeddingStore.search(searchRequest);

        List<Content> contents = searchResult.matches().stream()
                .map(EmbeddingMatch::embedded)
                .map(Content::from)
                .collect(toList());

        return applyTokenBudget(contents);
    }

    private List<Content> applyTokenBudget(List<Content> contents) {

        int tokenCount = 0;
        Set<String> paths = new HashSet<>();
        List<Content> result = new ArrayList<>();

        for (Content content : contents) {
            result.add(content);

            Metadata metadata = content.textSegment().metadata();
            String path = metadata.getString("relative_path");
            if (!paths.contains(path)) {
                paths.add(path);
                tokenCount += metadata.getInteger("whole_document_token_count");
                if (tokenCount > tokenBudget) {
                    result.remove(content);
                    paths.remove(path);
                    break;
                }
            }
        }

        return result;
    }
}
