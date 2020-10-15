import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        int countOfSubstrings = 1;

        for (int i = str.length() - 1; i >= 0 ; i--) {
            String substring = str.substring(i);

            int[] prefixFunc = prefixFunction(substring);
            int maxPrefixFunc = Arrays.stream(prefixFunc).max().getAsInt();

            countOfSubstrings += substring.length() - maxPrefixFunc;
        }

        System.out.println(countOfSubstrings);
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