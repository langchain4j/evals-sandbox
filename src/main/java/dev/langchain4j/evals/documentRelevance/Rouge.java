package dev.langchain4j.evals.documentRelevance;


import org.kie.trustyai.metrics.language.rouge.ROUGE;

public class Rouge {
    public static void main(String[] args) {
        String sentence1 = "The quick brown fox jumps over the lazy dog.";
        String sentence2 = "The quick brown fox jumps over the lazy frog.";

        ROUGE RougeScorer = new ROUGE(ROUGE.RougeTypes.ROUGE_LSUM);
        System.out.println(RougeScorer.calculate(sentence1, sentence2));
    }
}
