# My Journey – String Matching Homework

## Introduction

This homework focused on implementing and analyzing string matching algorithms and building a system that can intelligently choose the most suitable algorithm for a given scenario. At the beginning, I was familiar with the theoretical definitions of string matching algorithms, but I had limited experience implementing and comparing them in a structured and performance-oriented framework.

Through this assignment, I gained hands-on experience with algorithm design, performance analysis, and decision-making based on input characteristics.



## Learning Process and Implementation

### Boyer-Moore Algorithm

The most challenging and educational part of this homework was implementing the Boyer-Moore algorithm. I started by revisiting the theoretical background, especially the **bad character rule** and how it allows the algorithm to skip unnecessary comparisons.

I implemented the algorithm step by step, beginning with preprocessing the pattern to build the bad character table. During implementation, I paid special attention to edge cases such as:
- Pattern longer than text
- No match situations
- Repeated characters in the pattern

Testing the algorithm against the provided JSON test cases helped me verify correctness and understand how different inputs affect performance.



### GoCrazy Algorithm 

For the optional GoCrazy algorithm, I experimented with combining simple heuristics inspired by existing algorithms. The goal was not to outperform well-established algorithms in every case, but to explore how heuristic-based optimizations can work for specific pattern characteristics.

This part helped me think more creatively about algorithm design rather than strictly following textbook approaches.



## Pre-Analysis Strategy

I implemented a pre-analysis system that decides which algorithm to use before execution. My strategy considers several factors:

- Pattern length  
- Text length  
- Repeating prefixes in the pattern  
- Expected overhead of the algorithm  

For example:
- Very short patterns are handled efficiently by the Naive algorithm.
- Patterns with repeating prefixes favor KMP.
- Longer patterns in large texts benefit from Boyer-Moore or Rabin-Karp.

This section helped me understand that algorithm efficiency is not only about asymptotic complexity, but also about practical input characteristics and overhead.



## Research and External Resources

During this homework, I used several external resources to guide my implementation and understanding:

- Online articles and visual explanations of the Boyer-Moore algorithm
- Course lecture notes on string matching algorithms
- Discussions and examples explaining trade-offs between Naive, KMP, Rabin-Karp, and Boyer-Moore

I also used a Large Language Model (ChatGPT) to:
- Clarify parts of the Boyer-Moore algorithm logic
- Validate my understanding of preprocessing steps
- Get feedback on how to structure the pre-analysis strategy

The LLM was used as a learning aid and conceptual guide, not as a direct code generator.



## Challenges Faced

One of the main challenges was balancing correctness and efficiency. Some implementations initially worked for simple cases but failed on edge cases. Debugging these issues required careful tracing of indices and shifts.

Another challenge was designing the pre-analysis logic in a way that improves performance without adding excessive overhead. This taught me that sometimes a simpler decision rule can be more effective than a complex one.



## What I Learned

From this homework, I learned:
- How advanced string matching algorithms work in practice
- How preprocessing can drastically improve runtime performance
- How to evaluate algorithms not only by theory but also by real test results
- The importance of documenting research and development processes

Overall, this assignment strengthened both my algorithmic thinking and my practical software engineering skills.



## Feedback

This homework was challenging but very instructive. The provided test framework and comparison tables made it easier to understand algorithm behavior and performance differences. The requirement to document the learning journey encouraged honest reflection rather than focusing only on results.



**Name:** Minel Demirci  
**Student Number:** 23050121003
