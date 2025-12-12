# My Journey – String Matching Homework (CENG303)

## Introduction

This homework focused on implementing and analyzing different string matching algorithms,
including Boyer–Moore, and designing a pre-analysis system that selects the most suitable
algorithm depending on the input text and pattern.

The goal was not only to make the algorithms work correctly, but also to understand
their strengths, weaknesses, and performance differences in practice.  
We worked on this assignment together and discussed our design choices and implementation
details throughout the process.


## Boyer–Moore Implementation Experience

At the beginning, the Boyer–Moore algorithm was the most challenging part for us.
Although we had seen the theory in lectures, implementing both the bad character rule
and the good suffix rule in code required careful attention.

We started by implementing the bad character rule first, because it is simpler and easier
to test. After verifying that it worked correctly, we added the good suffix preprocessing
and shifting logic. We tested the algorithm with simple cases, such as short texts,
patterns longer than the text, and empty patterns, before running the full test suite.

Through this process, we learned how preprocessing can significantly reduce unnecessary
comparisons and why Boyer–Moore is especially effective for longer patterns and larger
alphabets.


## GoCrazy Algorithm Design

For the GoCrazy algorithm, we initially considered a more complex hybrid approach that
analyzes pattern repetition and alphabet size. However, we realized that this made the
code harder to understand and less suitable for a student-level assignment.

Therefore, we simplified the design and implemented a **hybrid strategy**:
- If the pattern is short, the algorithm switches to **KMP**.
- If the pattern is longer, it uses a **Sunday-style skip algorithm**.

This approach allowed us to demonstrate creativity while keeping the implementation
clean, readable, and easy to explain. The simplified version also performed well in
practice and passed all test cases.


## Pre-Analysis Strategy

The pre-analysis system was designed to choose the most appropriate algorithm before
running the actual search.

Our strategy considers:
- Pattern length
- Text length
- Repetition inside the pattern
- Estimated alphabet size

For example:
- Very short patterns use the **Naive** algorithm to avoid preprocessing overhead.
- Highly repetitive patterns are directed to **KMP**.
- Long texts with larger alphabets prefer **Boyer–Moore**.
- Long patterns in long texts may use **Rabin–Karp**.

We tried to balance decision accuracy with low overhead, since pre-analysis itself should
not become a performance bottleneck.


## Research and Resources Used

During this homework, we used the following resources:
- Course lecture notes on string matching algorithms
- Online algorithm explanations (general references such as Wikipedia and algorithm blogs)
- ChatGPT (LLM) to:
  - Clarify algorithm logic
  - Simplify complex implementations
  - Improve code readability and structure

All algorithm logic was written and understood by us, and the LLM was used as a learning
and support tool, not for blind copy-pasting.


## Personal Reflection

This homework was challenging but very educational for both of us. We struggled especially
with translating theoretical ideas into correct and efficient Java code. However, by testing
step by step and simplifying our designs when necessary, we gained confidence in implementing
complex algorithms.

Working together helped us discuss alternative solutions and better understand the trade-offs
between different algorithms. Overall, this assignment improved both our algorithmic thinking
and our practical coding skills.


## Student Information

Student 1  
Name: Minel Demirci  
Student Number: 23050121003  

Student 2  
Name: Filiz Tanıtmış  
Student Number: 23050131004
