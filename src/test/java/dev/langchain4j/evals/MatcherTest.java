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


            test(
                    matcher,
                    "I like apples.",
                    List.of("I like apples."),
                    true
            );

            test(
                    matcher,
                    "I like apples.",
                    List.of("I like bananas."),
                    false
            );

            test(
                    matcher,
                    "I like apples.",
                    List.of(),
                    false
            );

            test(
                    matcher,
                    "I like apples.",
                    List.of("I like apples.", "I like bananas."),
                    true
            );

            test(
                    matcher,
                    "I like apples.",
                    List.of("I like bananas.", "I like apples."),
                    true
            );

            test(
                    matcher,
                    "I like apples.",
                    List.of("I like apples.", "I like apples."),
                    true
            );

            test(
                    matcher,
                    "I like apples.",
                    List.of("I like cherries.", "I like bananas."),
                    false
            );

            test(
                    matcher,
                    "I like apples.",
                    List.of("I like apples.", "I like b.", "I like c.", "I like d.", "I like e.", "I like f."),
                    true
            );

            test(
                    matcher,
                    "I like apples.",
                    List.of("I like apples. I like b. I like c. I like d. I like e. I like f. I like g."),
                    true
            );

            test(
                    matcher,
                    "I like apples.",
                    List.of("I like b.", "I like c.", "I like d.", "I like e.", "I like f.", "I like apples."),
                    true
            );

            test(
                    matcher,
                    "I like apples.",
                    List.of("I like b. I like c. I like d. I like e. I like f. I like g. I like apples."),
                    true
            );


            // ground truth split across multiple retrieved (due to chunking)

            test(
                    matcher,
                    "I like apples. I like bananas.",
                    List.of("I like apples.", "I like bananas."),
                    // TODO count is as "match" only if both retrieved come from the same document and are consecutive?
                    true
            );

            test(
                    matcher,
                    "\nI like apples.\n\nI like bananas.\n", // TODO test more cases like this
                    // TODO count is as "match" only if both retrieved come from the same document and are consecutive?
                    List.of("I like apples.", "I like bananas."),
                    true
            );

            test(
                    matcher,
                    "I like apples. I like bananas.",
                    // TODO count is as "match" only if both retrieved come from the same document and are consecutive?
                    List.of("I like\napples.", "I like\nbananas."),
                    true
            );

            test(
                    matcher,
                    "I like apples. I like bananas.",
                    // TODO count is as "match" only if both retrieved come from the same document and are consecutive?
                    List.of("I like bananas.", "I like apples."),
                    true
            );

            test(
                    matcher,
                    "I like apples. I like bananas.",
                    List.of("I like apples. I like bananas.", "I like apples. I like bananas."),
                    true
            );

            test(
                    matcher,
                    "I like apples. I like bananas.",
                    // TODO count is as "match" only if both retrieved come from the same document and are consecutive?
                    List.of("I like apples.", "I like bananas.", "I like apples.", "I like bananas."),
                    true
            );

            test(
                    matcher,
                    "I like apples. I like bananas.",
                    // TODO count is as "match" only if both retrieved come from the same document and are consecutive?
                    List.of("I like apples.", "I like bananas.", "I like cherries."),
                    true
            );

            test(
                    matcher,
                    "I like apples. I like bananas.",
                    List.of("I like apples.", "I like cherries."),
                    false
            );

            test(
                    matcher,
                    "I like apples. I like bananas.",
                    List.of("I like bananas.", "I like cherries.", "I like apples."),
                    true // TODO count is as "match" only if both retrieved come from the same document and are consecutive?
            );


            // potential false positives

            test(
                    matcher,
                    "I like apples.",
                    List.of("I like pineapples."),
                    false
            );

            test(
                    matcher,
                    "I like apples.",
                    List.of("I do not like apples."),
                    false
            );

            test(
                    matcher,
                    "I like apples.",
                    List.of("I like bananas.", "He likes apples."),
                    false
            );
        }
    }

    private static void test(Matcher matcher,
                             String groundTruth,
                             List<String> retrieved,
                             boolean expectedToMatch) {
        boolean matched = matcher.match(groundTruth, retrieved);
        if (matched != expectedToMatch) {
            System.out.println("================================================================================");
            System.out.println("groundTruth=%s\nretrieved=%s\nexpectedToMatch=%s\nmatched=%s"
                    .formatted(groundTruth, retrieved, expectedToMatch, matched));
        }
    }

    static List<Matcher> matchers() {
        return List.of(
                new RougeMatcher("l")
//                new TfIdfMatcher(),
//                new SentenceMatcher()
        );
    }
}