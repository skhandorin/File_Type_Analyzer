import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();
        scanner.nextLine();
        Character[] symbols = Arrays.stream(scanner.nextLine().split(" "))
                .map(s -> s.charAt(0))
                .toArray(Character[]::new);
        int[] result = new int[symbols.length];

        for (int i = 0; i < symbols.length; i++) {
            String newWord = symbols[i] + word;

            for (int j = 0; j < newWord.length(); j++) {
                if (!KMPContains(word, newWord.substring(0, j + 1))) {
                    result[i] = newWord.length() - j;
                    break;
                }
            }
        }

        Arrays.stream(result)
                .forEach(i -> System.out.print(i + " "));
    }

    private static boolean KMPContains(String text, String pattern) {
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
}