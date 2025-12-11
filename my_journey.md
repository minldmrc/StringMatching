# My Journey – String Matching Homework (CENG303)

## Introduction

This homework focused on implementing and analyzing different string matching algorithms,
including Boyer–Moore, and designing a pre-analysis system that selects the most suitable
algorithm depending on the input text and pattern.

The goal was not only to make the algorithms work correctly, but also to understand
their strengths, weaknesses, and performance differences in practice.


## Boyer–Moore Implementation Experience

At the beginning, the Boyer–Moore algorithm was the most challenging part for me.
Although I had seen the theory in lectures, implementing both the **bad character rule**
and the **good suffix rule** in code required careful attention.

I started by implementing the bad character rule first, because it is simpler and easier
to test. After verifying that it worked correctly, I added the good suffix preprocessing
and shifting logic. I tested the algorithm with simple cases, such as short texts,
patterns longer than the text, and empty patterns, before running the full test suite.

Through this process, I learned how preprocessing can significantly reduce unnecessary
comparisons and why Boyer–Moore is especially effective for longer patterns and larger
alphabets.



## GoCrazy Algorithm Design

For the GoCrazy algorithm, I initially considered a more complex hybrid approach that
analyzes pattern repetition and alphabet size. However, I realized that this made the
code harder to understand and less suitable for a student-level assignment.

Therefore, I simplified the design and implemented a **hybrid strategy**:
- If the pattern is short, the algorithm switches to **KMP**.
- If the pattern is longer, it uses a **Sunday-style skip algorithm**.

This approach allowed me to demonstrate creativity while keeping the implementation
clean, readable, and easy to explain. The simplified version also performed well in
practice and passed all test cases.



## Pre-Analysis Strategy

The pre-analysis system was designed to choose the most appropriate algorithm before
running the actual search.

My strategy considers:
- Pattern length
- Text length
- Repetition inside the pattern
- Estimated alphabet size

For example:
- Very short patterns use the **Naive** algorithm to avoid preprocessing overhead.
- Highly repetitive patterns are directed to **KMP**.
- Long texts with larger alphabets prefer **Boyer–Moore**.
- Long patterns in long texts may use **Rabin–Karp**.

I tried to balance decision accuracy with low overhead, since pre-analysis itself should
not become a performance bottleneck.


## Research and Resources Used

During this homework, I used the following resources:
- Course lecture notes on string matching algorithms
- Online algorithm explanations (general references such as Wikipedia and algorithm blogs)
- ChatGPT (LLM) to:
  - Clarify algorithm logic
  - Simplify complex implementations
  - Improve code readability and structure

All algorithm logic was written and understood by me, and the LLM was used as a learning
and support tool, not for blind copy-pasting.



## Personal Reflection

This homework was challenging but very educational. I struggled especially with translating
theoretical ideas into correct and efficient Java code. However, by testing step by step
and simplifying my designs when necessary, I gained confidence in implementing complex
algorithms.

I learned that **clarity and correctness are more important than overcomplicated ideas**,
especially in algorithm design. Overall, this assignment helped me understand string
matching algorithms much better than just studying them theoretically.



## Student Information

Name: Minel Demirci  
Student Number: 23050121003
