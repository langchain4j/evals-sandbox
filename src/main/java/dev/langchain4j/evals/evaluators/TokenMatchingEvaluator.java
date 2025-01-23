package dev.langchain4j.evals.evaluators;

import dev.langchain4j.data.document.Document;
import opennlp.tools.tokenize.Tokenizer;
import org.kie.trustyai.metrics.language.utils.tokenizers.TokenizerUtils;

import java.util.*;

public class TokenMatchingEvaluator implements RetrievalEvaluator {

    private static Tokenizer tokenizer;

    public TokenMatchingEvaluator() {
        tokenizer = TokenizerUtils.getCommonsTokenizer();
    }

    @Override
    public Map<String, Double> evaluate(List<Document> groundTruthDocuments, List<Document> retrievedDocuments) {

        Set<String> groundTruthTokens = new HashSet<>();
        Set<String> retrievedTokens = new HashSet<>();

        assert tokenizer != null;
        for (Document document : groundTruthDocuments){
            groundTruthTokens.addAll(List.of(tokenizer.tokenize(document.text())));
        }

        for (Document retrievedDocument : retrievedDocuments){
            retrievedTokens.addAll(List.of(tokenizer.tokenize(retrievedDocument.text())));
        }

        Set<String> commonTokens = new HashSet<>();

        for (String groundTruthToken : groundTruthTokens) {
            for (String retrievedToken : retrievedTokens) {
                if (groundTruthToken.equals(retrievedToken)) {
                    commonTokens.add(groundTruthToken);
                }
            }
        }

        double precision = 1 * (commonTokens.size() / (double) (retrievedTokens.size()));
        double recall =  1 * (commonTokens.size() / (double) (groundTruthTokens.size()));

        return Map.of("Precision", precision, "Recall", recall);
    }
}
