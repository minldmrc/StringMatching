/**
 * PreAnalysis interface for students to implement their algorithm selection logic
 * 
 * Students should analyze the characteristics of the text and pattern to determine
 * which algorithm would be most efficient for the given input.
 * 
 * The system will automatically use this analysis if the chooseAlgorithm method
 * returns a non-null value.
 */
public abstract class PreAnalysis {
    
    /**
     * Analyze the text and pattern to choose the best algorithm
     * 
     * @param text The text to search in
     * @param pattern The pattern to search for
     * @return The name of the algorithm to use (e.g., "Naive", "KMP", "RabinKarp", "BoyerMoore", "GoCrazy")
     *         Return null if you want to skip pre-analysis and run all algorithms
     * 
     * Tips for students:
     * - Consider the length of the text and pattern
     * - Consider the characteristics of the pattern (repeating characters, etc.)
     * - Consider the alphabet size
     * - Think about which algorithm performs best in different scenarios
     */
    public abstract String chooseAlgorithm(String text, String pattern);
    
    /**
     * Get a description of your analysis strategy
     * This will be displayed in the output
     */
    public abstract String getStrategyDescription();
}


/**
 * Default implementation that students should modify
 * This is where students write their pre-analysis logic
 */
/**
 * Default implementation that students should modify
 * This is where students write their pre-analysis logic
 */
class StudentPreAnalysis extends PreAnalysis {

    @Override
    public String chooseAlgorithm(String text, String pattern) {
        // Safety checks
        if (text == null || pattern == null) {
            return "Naive";
        }

        int n = text.length();
        int m = pattern.length();

        // Extremely simple / degenerate cases
        if (m == 0) {
            // Empty pattern: every position matches.
            // Any algorithm is fine, choose Naive (no preprocessing overhead).
            return "Naive";
        }

        if (m > n) {
            // Pattern longer than text: no real search work, Naive is fine.
            return "Naive";
        }

        // Very short patterns: Naive is usually fastest because
        // preprocessing cost of others is not worth it.
        if (m <= 3) {
            return "Naive";
        }

        // Small text & small pattern: again preprocessing is overhead.
        if (n < 200 && m < 20) {
            return "Naive";
        }

        // Compute some characteristics of the input
        int alphabetSize = estimateAlphabetSize(text, pattern);
        boolean hasRepPrefix = hasRepeatingPrefix(pattern);
        boolean highlyRepeating = isHighlyRepeatingPattern(pattern);

        // Patterns with strong repetition (like "aaaaabaaa" or "abababab")
        // are good candidates for KMP because its prefix-function is very effective.
        if (hasRepPrefix || highlyRepeating) {
            // For very long texts KMP is very stable.
            if (n > 2000) {
                return "KMP";
            }
        }

        // Very long text and reasonably long pattern
        // If alphabet is large, Boyer-Moore typically shines.
        if (n > 5000 && m > 15) {
            if (alphabetSize > 40) {
                return "BoyerMoore";
            } else {
                // On small alphabets, KMP is more stable.
                return "KMP";
            }
        }

        // Long pattern + long-ish text:
        // Rabin-Karp can perform well on such inputs (especially for average-case).
        if (m > 30 && n > 1000) {
            return "RabinKarp";
        }

        // Medium/large text, medium pattern:
        // Choose between BoyerMoore and KMP depending on alphabet size.
        if (n > 1000 && m >= 5) {
            if (alphabetSize > 30) {
                return "BoyerMoore";
            } else {
                return "KMP";
            }
        }

        // Default: Boyer-Moore is generally a strong all-round choice.
        return "BoyerMoore";
    }

    @Override
    public String getStrategyDescription() {
        return "Student strategy: use Naive for tiny inputs, KMP for highly "
                + "repetitive patterns or small alphabets, Boyer-Moore for long "
                + "texts with large alphabets, Rabin-Karp for long patterns in "
                + "long texts, and choose defaults based on text/pattern length.";
    }

    // ---------------- helper methods ----------------

    /**
     * Rough estimate of alphabet size by counting distinct characters
     * in both text and pattern. O(n + m).
     */
    private int estimateAlphabetSize(String text, String pattern) {
        boolean[] seen = new boolean[256];
        int count = 0;

        for (int i = 0; i < text.length(); i++) {
            int idx = text.charAt(i) & 0xFF;
            if (!seen[idx]) {
                seen[idx] = true;
                count++;
            }
        }

        for (int i = 0; i < pattern.length(); i++) {
            int idx = pattern.charAt(i) & 0xFF;
            if (!seen[idx]) {
                seen[idx] = true;
                count++;
            }
        }

        return count;
    }

    /**
     * Check whether the pattern has a repeating prefix.
     * Example: "ababab", "aaaaabc" etc.
     */
    private boolean hasRepeatingPrefix(String pattern) {
        int m = pattern.length();
        if (m < 4) return false;

        int prefixLen = m / 2;
        String prefix = pattern.substring(0, prefixLen);
        String rest = pattern.substring(prefixLen);
        return rest.contains(prefix);
    }

    /**
     * Check if a single character dominates the pattern
     * (for example 'a' in "aaaaaabaaa").
     */
    private boolean isHighlyRepeatingPattern(String pattern) {
        int m = pattern.length();
        if (m == 0) return false;

        int[] freq = new int[256];
        for (int i = 0; i < m; i++) {
            freq[pattern.charAt(i) & 0xFF]++;
        }

        int maxFreq = 0;
        for (int f : freq) {
            if (f > maxFreq) maxFreq = f;
        }

        // If one character covers >= 70% of the pattern,
        // consider it "highly repeating".
        return maxFreq * 10 >= m * 7;
    }
}



/**
 * Example implementation showing how pre-analysis could work
 * This is for demonstration purposes
 */
class ExamplePreAnalysis extends PreAnalysis {

    @Override
    public String chooseAlgorithm(String text, String pattern) {
        int textLen = text.length();
        int patternLen = pattern.length();

        // Simple heuristic example
        if (patternLen <= 3) {
            return "Naive"; // For very short patterns, naive is often fastest
        } else if (hasRepeatingPrefix(pattern)) {
            return "KMP"; // KMP is good for patterns with repeating prefixes
        } else if (patternLen > 10 && textLen > 1000) {
            return "RabinKarp"; // RabinKarp can be good for long patterns in long texts
        } else {
            return "Naive"; // Default to naive for other cases
        }
    }

    private boolean hasRepeatingPrefix(String pattern) {
        if (pattern.length() < 2) return false;

        // Check if first character repeats
        char first = pattern.charAt(0);
        int count = 0;
        for (int i = 0; i < Math.min(pattern.length(), 5); i++) {
            if (pattern.charAt(i) == first) count++;
        }
        return count >= 3;
    }

    @Override
    public String getStrategyDescription() {
        return "Example strategy: Choose based on pattern length and characteristics";
    }
}

/**
 * Instructor's pre-analysis implementation (for testing purposes only)
 * Students should NOT modify this class
 */
class InstructorPreAnalysis extends PreAnalysis {

    @Override
    public String chooseAlgorithm(String text, String pattern) {
        // This is a placeholder for instructor testing
        // Students should focus on implementing StudentPreAnalysis
        return null;
    }

    @Override
    public String getStrategyDescription() {
        return "Instructor's testing implementation";
    }
}
