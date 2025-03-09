package dev.langchain4j.evals;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.evals.evaluators.AnswerSimilarityEvaluator;
import dev.langchain4j.model.openai.OpenAiChatModel;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;

public class GenerationDemo {
    public static void main(String[] args) {
        String apiKey = System.getenv("OPENAI_API_KEY");

        OpenAiChatModel model = OpenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName(GPT_4_O_MINI)
                .build();
//        StringBuilder context = new StringBuilder();
//        for (TextSegment textSegment : Dataset.get().get(0).expectedContextResults()) {
//            context.append(textSegment.text()).append("\n");
//        }
//        System.out.println("Given this context:\n\n"+context+"Answer this question:\n\n"+Dataset.get().get(0).query());
//        System.out.println(model.generate("Given this context:\n\n"+context+"\n Answer this question:\n"+Dataset.get().get(0).query()));

        for (DatasetEntry entry : Dataset.get()) {
            StringBuilder context = new StringBuilder();
            for (TextSegment textSegment : entry.expectedContextResults()) {
                context.append(textSegment.text()).append("\n");
            }
            String generatedAnswer = model.generate("Given this context:\n\n"+context+"\n Answer this question:\n"+Dataset.get().get(0).query());
            System.out.println("---------------------------------------");
            AnswerSimilarityEvaluator ase = new AnswerSimilarityEvaluator();
            System.out.println("Ground truth: "+entry.answer());
            System.out.println("Generated answer: "+generatedAnswer);
            System.out.println("Similarity: "+ase.evaluate(entry.answer(), generatedAnswer));
        }
    }
}
