import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Naive extends Solution {
    static {
        SUBCLASSES.add(Naive.class);
        System.out.println("Naive registered");
    }

    public Naive() {
    }

    @Override
    public String Solve(String text, String pattern) {
        List<Integer> indices = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();

        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }
            if (j == m) {
                indices.add(i);
            }
        }

        return indicesToString(indices);
    }
}

class KMP extends Solution {
    static {
        SUBCLASSES.add(KMP.class);
        System.out.println("KMP registered");
    }

    public KMP() {
    }

    @Override
    public String Solve(String text, String pattern) {
        List<Integer> indices = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();

        // Handle empty pattern - matches at every position
        if (m == 0) {
            for (int i = 0; i <= n; i++) {
                indices.add(i);
            }
            return indicesToString(indices);
        }

        // Compute LPS (Longest Proper Prefix which is also Suffix) array
        int[] lps = computeLPS(pattern);

        int i = 0; // index for text
        int j = 0; // index for pattern

        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }

            if (j == m) {
                indices.add(i - j);
                j = lps[j - 1];
            } else if (i < n && text.charAt(i) != pattern.charAt(j)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        return indicesToString(indices);
    }

    private int[] computeLPS(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int len = 0;
        int i = 1;

        lps[0] = 0;

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }
}

class RabinKarp extends Solution {
    static {
        SUBCLASSES.add(RabinKarp.class);
        System.out.println("RabinKarp registered.");
    }

    public RabinKarp() {
    }

    private static final int PRIME = 101; // A prime number for hashing

    @Override
    public String Solve(String text, String pattern) {
        List<Integer> indices = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();

        // Handle empty pattern - matches at every position
        if (m == 0) {
            for (int i = 0; i <= n; i++) {
                indices.add(i);
            }
            return indicesToString(indices);
        }

        if (m > n) {
            return "";
        }

        int d = 256; // Number of characters in the input alphabet
        long patternHash = 0;
        long textHash = 0;
        long h = 1;

        // Calculate h = d^(m-1) % PRIME
        for (int i = 0; i < m - 1; i++) {
            h = (h * d) % PRIME;
        }

        // Calculate hash value for pattern and first window of text
        for (int i = 0; i < m; i++) {
            patternHash = (d * patternHash + pattern.charAt(i)) % PRIME;
            textHash = (d * textHash + text.charAt(i)) % PRIME;
        }

        // Slide the pattern over text one by one
        for (int i = 0; i <= n - m; i++) {
            // Check if hash values match
            if (patternHash == textHash) {
                // Check characters one by one
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    indices.add(i);
                }
            }

            // Calculate hash value for next window
            if (i < n - m) {
                textHash = (d * (textHash - text.charAt(i) * h) + text.charAt(i + m)) % PRIME;

                // Convert negative hash to positive
                if (textHash < 0) {
                    textHash = textHash + PRIME;
                }
            }
        }

        return indicesToString(indices);
    }
}

/**
 * TODO: Implement Boyer-Moore algorithm
 * This is a homework assignment for students
 */
class BoyerMoore extends Solution {
    static {
        SUBCLASSES.add(BoyerMoore.class);
        System.out.println("BoyerMoore registered");
    }

    public BoyerMoore() {
    }

    @Override
    public String Solve(String text, String pattern) {
        List<Integer> indices = new ArrayList<>();

        int n = text.length();
        int m = pattern.length();

        // If pattern is empty tehn match at every position
        if (m == 0) {
            for (int i = 0; i <= n; i++) {
                indices.add(i);
            }
            return indicesToString(indices);
        }

        // If text is empty or pattern longer than text then no match
        if (n == 0 || m > n) {
            return indicesToString(indices);
        }

        char[] txt = text.toCharArray();
        char[] pat = pattern.toCharArray();

        // Build bad character and good suffix tables bellow
        int[] badChar = buildBadCharTable(pat);
        int[] suffix = new int[m];
        boolean[] prefix = new boolean[m];
        buildGoodSuffixTable(pat, suffix, prefix);

        int i = 0; // current window position in the text

        while (i <= n - m) {
            int j;

            // Compare pattern and text from right to left
            for (j = m - 1; j >= 0; j--) {
                if (txt[i + j] != pat[j]) {
                    break;
                }
            }

            // Full match found
            if (j < 0) {
                indices.add(i);
                i += 1; // shift by 1 to allow overlapping matches
            }
            else {
                // Bad character shift
                int badCharShift = j - badChar[txt[i + j] & 0xFF];
                if (badCharShift < 1) badCharShift = 1;

                // Good suffix shift
                int goodSuffixShift = 0;
                int suffixLen = m - 1 - j;
                if (suffixLen > 0) {
                    goodSuffixShift = calculateGoodSuffixShift(j, m, suffix, prefix);
                }

                // Take the maximum of the two shifts
                int shift = Math.max(badCharShift, goodSuffixShift);
                if (shift< 1) shift = 1;

                i += shift;
            }
        }

        return indicesToString(indices);
    }


    // BAD CHARACTER TABLE

    // Stores the last index of each character in the pattern.
    // If char does not appear, value stays -1 so
    private int[] buildBadCharTable(char[] pat) {
        int[] badChar = new int[256];

        // Set all characters as "not found"
        Arrays.fill(badChar, -1);

        // Store the last position of each character in pattern
        for (int i = 0; i < pat.length; i++) {
            badChar[pat[i] & 0xFF] = i;
        }

        return badChar;
    }


    // GOOD SUFFIX PREPROCESSING

    private void buildGoodSuffixTable(char[] pat, int[] suffix, boolean[] prefix) {
        int m = pat.length;

        // Initialize arrays
        Arrays.fill(suffix, -1);
        Arrays.fill(prefix, false);

        // Find all suffixes that match inside the pattern
        for (int i = 0; i < m - 1; i++) {
            int j = i;
            int k = 0; // length of current matching suffix

            // Compare backwards: pat[j] vs pat[m-1-k]
            while (j >= 0 && pat[j] == pat[m - 1 - k]) {
                j--;
                k++;
                suffix[k] = j + 1; // store start index
            }

            // If we reached the beginning, this suffix is also a prefix
            if (j == -1) {
                prefix[k] = true;
            }
        }
    }


    // GOOD SUFFIX SHIFT

    private int calculateGoodSuffixShift(int j, int m, int[] suffix, boolean[] prefix) {
        int suffixLen = m - 1 - j;

        // Case 1: another substring in pattern matches the suffix
        if (suffix[suffixLen] != -1) {
            return j + 1 - suffix[suffixLen];
        }

        // Case 2: find prefix that matches a suffix segment
        for (int r = j + 2; r <= m - 1; r++) {
            int len = m - r;
            if (prefix[len]) {
                return r;
            }
        }

        // Case 3: no match → shift full pattern length
        return m;
    }
}


/**
 * TODO: Implement your own creative string matching algorithm
 * This is a homework assignment for students
 * Be creative! Try to make it efficient for specific cases
 */
class GoCrazy extends Solution {
    static {
        SUBCLASSES.add(GoCrazy.class);
        System.out.println("GoCrazy registered");
    }

    public GoCrazy() {}

    @Override
    public String Solve(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        // Empty pattern is match everywhere
        if (m == 0) {
            List<Integer> indices = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                indices.add(i);
            }
            return indicesToString(indices);
        }

        // If text is empty or pattern longer than text so no match
        if (n == 0 || m > n) {
            return "";
        }

        // Simple hybrid rule:
        // If the pattern is short, use KMP.
        // If the pattern is longer, use a Sunday-style algorithm.
        if (m < 5) {
            return kmpMode(text, pattern);
        } else {
            return sundayMode(text, pattern);
        }
    }

    // ---------------------
    // KMP MODE
    // --------------------------
    private String kmpMode(String text, String pattern) {
        List<Integer> indices = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();

        int[] lps = buildLPS(pattern);
        int i = 0, j = 0;

        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++; j++;
            }

            if (j == m) {
                indices.add(i - j);
                j = lps[j - 1];
            } else if (i < n && text.charAt(i) != pattern.charAt(j)) {
                if (j != 0) j = lps[j - 1];
                else i++;
            }
        }

        return indicesToString(indices);
    }

    private int[] buildLPS(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int len = 0, i = 1;
        lps[0] = 0;

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) len = lps[len - 1];
                else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    // ---------------------
    // SUNDAY MODE
    // --------------------
    private String sundayMode(String text, String pattern) {
        List<Integer> indices = new ArrayList<>();

        int n = text.length();
        int m = pattern.length();

        char[] txt = text.toCharArray();
        char[] pat = pattern.toCharArray();

        int[] shift = buildSundayShiftTable(pat);

        int i = 0;
        while (i <= n - m) {
            int j;

            // Compare from right to left
            for (j = m - 1; j >= 0; j--) {
                if (txt[i + j] != pat[j]) break;
            }

            // Full match
            if (j < 0) {
                indices.add(i);
                i += 1;
            }
            else {
                int nextIndex = i + m;
                int s = (nextIndex < n) ? shift[txt[nextIndex] & 0xFF] : 1;
                if (s <= 0) s = 1;
                i += s;
            }
        }

        return indicesToString(indices);
    }

    private int[] buildSundayShiftTable(char[] pat) {
        int m = pat.length;
        int[] shift = new int[256];

        Arrays.fill(shift, m + 1);

        for (int i = 0; i < m; i++) {
            shift[pat[i] & 0xFF] = m - i;
        }
        return shift;
    }
}





