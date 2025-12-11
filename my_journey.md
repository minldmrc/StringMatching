# My Journey – CENG303 String Matching Homework

## Introduction

This homework assignment focused on implementing and analyzing classical string matching algorithms, including Naive, KMP, Rabin-Karp, and Boyer-Moore, as well as designing a custom algorithm and a pre-analysis strategy to select the most suitable algorithm based on input characteristics.

The project was implemented in Java using a provided framework that included a common `Solution` base class, automated test runners, and a comprehensive set of test cases. Through this assignment, I explored how different algorithms behave under varying conditions such as short patterns, repetitive structures, long texts, and large alphabets.



## Boyer-Moore Implementation Experience

Implementing the Boyer-Moore algorithm was the most technically challenging part of this homework. While the bad character rule was relatively straightforward, the good suffix rule required much deeper understanding.

I had to carefully study how suffix and prefix tables are constructed and how they interact during mismatch handling. Small mistakes in index calculations or shift logic easily caused incorrect matches or infinite loops. To overcome this, I tested the algorithm on very small strings, printed intermediate tables, and verified each rule separately before combining them.

In the end, my implementation successfully passed all test cases and performed especially well on long-pattern scenarios, which confirmed that the algorithm was implemented correctly.



## Designing the GoCrazy Algorithm

The most enjoyable part of this homework was designing my own algorithm, called **GoCrazy**. Instead of inventing a completely new algorithm, I chose a hybrid approach that combines existing ideas in a simple and understandable way.

My final design decision was intentionally minimal:

- If the pattern length is **short (< 5)** → use **KMP**
- Otherwise → use a **Sunday-style skipping algorithm**

I initially considered more complex pattern analysis (such as dominant characters or repetition detection), but I realized that such logic could make the code harder to explain and maintain. Since this is an undergraduate algorithms course, I simplified GoCrazy to a clean and student-readable hybrid.

Despite its simplicity, GoCrazy performed very well in practice. It was the fastest algorithm in several cases such as long texts, Unicode-heavy inputs, and large alphabets. This showed me that even simple heuristics can produce strong real-world performance.



## Pre-Analysis Strategy

The purpose of the pre-analysis module was to select the fastest algorithm **before execution**, based on quick observations of the input.

My strategy was designed with two priorities:
1. **Very low overhead**
2. **Reasonable accuracy**

The main factors I considered were:
- Pattern length
- Text length
- Repetitive structure in the pattern
- Estimated alphabet size

For example:
- Very short patterns → Naive
- Repetitive patterns → KMP
- Long text + long pattern → Boyer-Moore
- Large alphabet scenarios → sometimes GoCrazy or Boyer-Moore

Although the pre-analysis does not always choose the absolute fastest algorithm, its overhead is extremely small, and it demonstrates how heuristic-based decision making can be effective in algorithm selection.



## Research and External Resources

During this homework, I used several external resources for **conceptual understanding only**, including:

- *CLRS – Introduction to Algorithms* (Boyer-Moore theory)
- GeeksForGeeks articles on KMP, Rabin-Karp, and Boyer-Moore
- University lecture notes and online tutorials

I also used **ChatGPT** as a learning assistant to:
- Clarify edge cases
- Compare alternative implementations
- Improve code readability
- Brainstorm hybrid algorithm ideas

All final code was written, tested, and debugged manually by me. The LLM was used responsibly as a support tool, not as an automatic code generator.



## What I Learned

This assignment taught me several important lessons:

- Algorithm performance is highly input-dependent.
- Preprocessing can be both a strength and a weakness.
- Even simple heuristics can significantly improve performance.
- Debugging algorithmic logic requires patience and systematic testing.
- Hybrid approaches can outperform classical algorithms in real test cases.

I also learned not to underestimate “simple” algorithms like Naive — in many short-pattern scenarios, it outperformed more advanced methods due to lower overhead.



## Honest Reflection

At the beginning, this homework felt overwhelming, especially the Boyer-Moore implementation. I struggled with understanding the good suffix rule and spent a lot of time fixing subtle bugs.

However, once everything started working, the assignment became very satisfying. Seeing my implementations pass all tests and watching my custom algorithm win some cases was genuinely motivating.

Although this homework took much more effort than I initially expected, it significantly improved my confidence in implementing complex algorithms and analyzing performance trade-offs.

Overall, this was one of the most educational assignments I have completed so far.



**Name:** Minel Demirci  
**Student Number:** 23050121003  
