package analyzer;

public class RabinKarp {
    private static final int A = 53;
    private static final long M = 1_000_000_009L;

    private static long charToLong(char ch) {
//        return ch - 'A' + 1;
        return ch;
    }


    public static long getHash(String s) {
        long hash = 0;
        long pow = 1;

        for (int i = 0; i < s.length(); i++) {
            hash = (hash + charToLong(s.charAt(i)) * pow) % M;
            pow = pow * A % M;
        }

        return hash;
    }

    public static boolean contains(String text, String pattern) {
        if (text.length() < pattern.length()) {
            return false;
        }

        long patternHash = 0L;
        long substrHash = 0L;
        long pow = 1L;

        for (int i = 0; i < pattern.length(); i++) {
            patternHash = (patternHash + charToLong(pattern.charAt(i)) * pow) % M;
            substrHash = (substrHash + charToLong(text.charAt(text.length() - pattern.length() + i)) * pow) % M;

            if (i != pattern.length() - 1) {
                pow = pow * A % M;
            }
        }

        for (int i = text.length(); i >= pattern.length(); i--) {
            if (patternHash == substrHash &&
                    pattern.equals(text.substring(i - pattern.length(), i))
            ) {
                return true;
            }

            if (i > pattern.length()) {
                substrHash = (substrHash - charToLong(text.charAt(i - 1)) * pow % M + M) * A % M;
                substrHash = (charToLong(text.charAt(i - 1 - pattern.length())) + substrHash) % M;
            }
        }
        return false;
    }
}
