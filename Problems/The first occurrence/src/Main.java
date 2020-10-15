import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        String pattern = scanner.nextLine();
        String text = scanner.nextLine();

        System.out.println(searchPattern(text, pattern));
    }

    private static int searchPattern(String text, String pattern) {
        if (text.length() < pattern.length()) {
            return -1;
        }

        for (int i = 0; i < text.length() - pattern.length() + 1; i++) {
            boolean patternIsFound = true;

            for (int j = 0; j < pattern.length(); j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    patternIsFound = false;
                    break;
                }
            }

            if (patternIsFound) {
                return i;
            }
        }

        return -1;
    }
}