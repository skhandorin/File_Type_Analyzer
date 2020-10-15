import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        String pattern = scanner.nextLine();
        String text = scanner.nextLine();

        List<Integer> occurrences = searchPatternsOccurrences(text, pattern);
        System.out.println(occurrences.size());
        occurrences.forEach(integer -> System.out.print(integer + " "));
    }

    private static List<Integer> searchPatternsOccurrences(String text, String pattern) {
        List<Integer> occurrences = new ArrayList<>();

        if (pattern.isEmpty()) {
            occurrences.add(0);
            return occurrences;
        }

        int i = 0;
        while (i < text.length() - pattern.length() + 1) {
            boolean patternIsFound = true;

            for (int j = 0; j < pattern.length(); j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    patternIsFound = false;
                    break;
                }
            }

            if (patternIsFound) {
                occurrences.add(i);
            }
            i++;
        }

        return occurrences;
    }

}