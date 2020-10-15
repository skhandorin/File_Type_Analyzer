package analyzer;

public class Naive {
    private static boolean naiveContains(String text, String pattern) {
        for (int i = 0; i < text.length() - pattern.length() + 1; i++) {
            boolean isCoincided = true;
            for (int j = 0; j < pattern.length(); j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    isCoincided = false;
                    break;
                }
            }

            if (isCoincided) {
                return true;
            }
        }

        return false;
    }

}
