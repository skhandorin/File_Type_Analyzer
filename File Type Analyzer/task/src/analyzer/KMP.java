package analyzer;

public class KMP {
    private static int[] prefixFunction(String str) {
        int[] prefixFunc = new int[str.length()];

        for (int i = 1; i < str.length(); i++) {
            int j = prefixFunc[i - 1];
            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                j = prefixFunc[j - 1];
            }
            if (str.charAt(i) == str.charAt(j)) {
                j++;
            }
            prefixFunc[i] = j;
        }

        return prefixFunc;
    }

    public static boolean contains(String text, String pattern) {
        int[] prefixFunc = prefixFunction(pattern);
        int j = 0;

        for (int i = 0; i < text.length(); i++) {
            while (j > 0 && pattern.charAt(j) != text.charAt(i)) {
                j = prefixFunc[j - 1];
            }
            if (text.charAt(i) == pattern.charAt(j)) {
                j += 1;
            }
            if (j == pattern.length()) {
                return true;
            }
        }

        return false;
    }

}
