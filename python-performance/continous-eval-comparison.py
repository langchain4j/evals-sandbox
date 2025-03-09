from continuous_eval.metrics.retrieval import PrecisionRecallF1
import time

datum = {
    "question": "What is the capital of France?",
    "retrieved_context": [
        "Paris is the capital of France and its largest city.",
        "Lyon is a major city in France.",
    ],
    "ground_truth_context": ["Paris is the capital of France."],
    "answer": "Paris",
    "ground_truths": ["Paris"],
}

start_time = time.time()

metric = PrecisionRecallF1()

print(metric(**datum))
print("--- %s seconds ---" % (time.time() - start_time))