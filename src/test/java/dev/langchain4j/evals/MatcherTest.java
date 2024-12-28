package dev.langchain4j.evals;

import java.util.List;

class MatcherTest {

    public static void main(String[] args) {

        for (Matcher matcher : matchers()) {

            System.out.println("================================================================================");
            System.out.println("================================================================================");
            System.out.println(matcher.getClass());
            System.out.println("================================================================================");
            System.out.println("================================================================================");

            // single ground truth

            test(
                    matcher,
                    List.of("I like apples."),
                    List.of("I like apples."),
                    1.0
            );

            test(
                    matcher,
                    List.of("I like apples."),
                    List.of("I like bananas."),
                    0.0
            );

            test(
                    matcher,
                    List.of("I like apples."),
                    List.of(),
                    0.0
            );

            test(
                    matcher,
                    List.of("I like apples."),
                    List.of("I like apples.", "I like bananas."),
                    1.0
            );

            test(
                    matcher,
                    List.of("I like apples."),
                    List.of("I like bananas.", "I like apples."),
                    1.0
            );

            test(
                    matcher,
                    List.of("I like apples."),
                    List.of("I like apples.", "I like apples."),
                    1.0
            );

            test(
                    matcher,
                    List.of("I like apples."),
                    List.of("I like cherries.", "I like bananas."),
                    0.0
            );

            test(
                    matcher,
                    List.of("I like apples."),
                    List.of("I like apples.", "I like b.", "I like c.", "I like d.", "I like e.", "I like f."),
                    1.0
            );

            test(
                    matcher,
                    List.of("I like apples."),
                    List.of("I like apples. I like b. I like c. I like d. I like e. I like f. I like g."),
                    1.0
            );

            test(
                    matcher,
                    List.of("I like apples."),
                    List.of("I like b.", "I like c.", "I like d.", "I like e.", "I like f.", "I like apples."),
                    1.0
            );

            test(
                    matcher,
                    List.of("I like apples."),
                    List.of("I like b. I like c. I like d. I like e. I like f. I like g. I like apples."),
                    1.0
            );


            // multiple ground truths

            test(
                    matcher,
                    List.of("I like apples.", "I like bananas."),
                    List.of("I like apples.", "I like bananas."),
                    1.0
            );

            test(
                    matcher,
                    List.of("I like apples.", "I like bananas."),
                    List.of("I like apples. I like bananas."),
                    1.0
            );

            test(
                    matcher,
                    List.of("I like apples.", "I like bananas."),
                    List.of("I like apples. I like cherries. I like bananas."),
                    1.0
            );

            test(
                    matcher,
                    List.of("I like apples.", "I like bananas."),
                    List.of("I like bananas.", "I like apples."),
                    1.0
            );

            test(
                    matcher,
                    List.of("I like apples.", "I like bananas."),
                    List.of("I like apples.", "I like bananas.", "I like cherries."),
                    1.0
            );

            test(
                    matcher,
                    List.of("I like apples.", "I like bananas."),
                    List.of("I like bananas.", "I like cherries.", "I like apples."),
                    1.0
            );

            test(
                    matcher,
                    List.of("I like apples.", "I like bananas."),
                    List.of("I like apples.", "I like cherries."),
                    0.5
            );

            test(
                    matcher,
                    List.of("I like apples.", "I like bananas."),
                    List.of("I like cherries.", "I like apples."),
                    0.5
            );

            test(
                    matcher,
                    List.of("I like apples.", "I like bananas."),
                    List.of("I like melons.", "I like cherries."),
                    0.0
            );

            test(
                    matcher,
                    List.of("I like apples.", "I like bananas."),
                    List.of(),
                    0.0
            );


            // single ground truth, multiple sentences

            test(
                    matcher,
                    List.of("I like apples. I like bananas."),
                    List.of("I like apples.", "I like bananas."),
                    // TODO count is as "match" only if both retrieved come from the same document and are consecutive?
                    1.0
            );

            test(
                    matcher,
                    List.of("\nI like apples.\n\nI like bananas.\n"), // TODO test more cases like this
                    // TODO count is as "match" only if both retrieved come from the same document and are consecutive?
                    List.of("I like apples.", "I like bananas."),
                    1.0
            );

            test(
                    matcher,
                    List.of("I like apples. I like bananas."),
                    // TODO count is as "match" only if both retrieved come from the same document and are consecutive?
                    List.of("I like\napples.", "I like\nbananas."),
                    1.0
            );

            test(
                    matcher,
                    List.of("I like apples. I like bananas."),
                    // TODO count is as "match" only if both retrieved come from the same document and are consecutive?
                    List.of("I like bananas.", "I like apples."),
                    1.0
            );

            test(
                    matcher,
                    List.of("I like apples. I like bananas."),
                    List.of("I like apples. I like bananas.", "I like apples. I like bananas."),
                    1.0
            );

            test(
                    matcher,
                    List.of("I like apples. I like bananas."),
                    // TODO count is as "match" only if both retrieved come from the same document and are consecutive?
                    List.of("I like apples.", "I like bananas.", "I like apples.", "I like bananas."),
                    1.0
            );

            test(
                    matcher,
                    List.of("I like apples. I like bananas."),
                    // TODO count is as "match" only if both retrieved come from the same document and are consecutive?
                    List.of("I like apples.", "I like bananas.", "I like cherries."),
                    1.0
            );

            test(
                    matcher,
                    List.of("I like apples. I like bananas."),
                    List.of("I like apples.", "I like cherries."),
                    0.0
            );

            test(
                    matcher,
                    List.of("I like apples. I like bananas."),
                    List.of("I like bananas.", "I like cherries.", "I like apples."),
                    0.0
            );


            // potential false positives

            test(
                    matcher,
                    List.of("I like apples."),
                    List.of("I like pineapples."),
                    0.0
            );

            test(
                    matcher,
                    List.of("I like apples."),
                    List.of("I do not like apples."),
                    0.0
            );

            test(
                    matcher,
                    List.of("I like apples."),
                    List.of("I like bananas.", "He likes apples."),
                    0.0
            );
        }
    }

    private static void test(Matcher matcher,
                             List<String> groundTruths,
                             List<String> allRetrieved,
                             double expectedScore) {
        double actualScore = matcher.match(groundTruths, allRetrieved);
        if (Double.compare(actualScore, expectedScore) != 0) {
            System.out.println("================================================================================");
            System.out.println("groundTruths=%s\nallRetrieved=%s\nexpectedScore=%s\nactualScore=%s"
                    .formatted(groundTruths, allRetrieved, expectedScore, actualScore));
        }
    }

    static List<Matcher> matchers() {
        return List.of(
                new ExactMatcher(),
                new ContainsMatcher(),
                new BagOfWordsMatcher(),
                new BagOfSentencesMatcher()
        );
    }
}