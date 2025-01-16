package dev.langchain4j.evals;



import dev.langchain4j.evals.customROUGE.ROUGE;

import java.util.List;

public class RougeMatcher implements Matcher {

    ROUGE RougeScorer;

    public RougeMatcher(String rougeType) {
        switch (rougeType) {
            case "1":
                this.RougeScorer = new ROUGE(ROUGE.RougeTypes.ROUGE1);
                break;
            case "2":
                this.RougeScorer = new ROUGE(ROUGE.RougeTypes.ROUGE2);
                break;
            default:
                this.RougeScorer = new ROUGE(ROUGE.RougeTypes.ROUGEL);
                break;
        }
    }

    @Override
    public boolean match(String groundTruth, List<String> retrieved) {
        double highesthScore = 0.0;
        for (String retrievedString : retrieved){
            double score = RougeScorer.calculate(groundTruth, retrievedString);
            if (score > highesthScore) {
                highesthScore = score;
            }
        }
        System.out.println(highesthScore);
        return highesthScore > 0.7;
    }
}
