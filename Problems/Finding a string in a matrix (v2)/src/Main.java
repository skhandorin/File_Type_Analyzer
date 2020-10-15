import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int patternRows;
        int patternColumns;
        int textRows;
        int textColumns;

        int[] rowsColumnsArr = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        patternRows = rowsColumnsArr[0];
        patternColumns = rowsColumnsArr[1];

        String[] pattern = new String[patternRows];
        for (int i = 0; i < patternRows; i++) {
            pattern[i] = scanner.nextLine();
        }

        rowsColumnsArr = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        textRows = rowsColumnsArr[0];
        textColumns = rowsColumnsArr[1];

        String[] text = new String[textRows];
        for (int i = 0; i < textRows; i++) {
            text[i] = scanner.nextLine();
        }

        System.out.println(countMatrixOccurrences(text, pattern));
    }

    private static int countMatrixOccurrences(String[] text, String[] pattern) {
        int countOcc = 0;
        int[] prefixFunc = calculatePrefixFunctionArr(pattern);
//        System.out.println(Arrays.toString(prefixFunc));

        for (int i = 0; i < text.length - pattern.length + 1; i++) {
            countOcc += countMatrixOccurrencesInRow(text, pattern, prefixFunc, i);

        }

        return countOcc;
    }

    private static int countMatrixOccurrencesInRow(String[] text, String[] pattern, int[] prefixFunc, int row) {
        int countOcc = 0;
        int j = 0;



        for (int i = 0; i < text[0].length(); i++) {
            while (j > 0 && !compareColumnInMatrix(pattern, j, text, i, row)) {
                j = prefixFunc[j - 1];
            }
            if (compareColumnInMatrix(pattern, j, text, i, row)) {
                j += 1;
            }
            if (j == pattern[0].length()) {
                countOcc++;
                j = prefixFunc[j - 1];
            }
        }

        return countOcc;
    }

    private static int[] calculatePrefixFunctionArr(String[] strArr) {
        int[] prefixFunc = new int[strArr[0].length()];

        for (int i = 1; i < strArr[0].length(); i++) {
            int j = prefixFunc[i - 1];
            while (j > 0 && !compareColumn(strArr, i, strArr, j)) {
                j = prefixFunc[j - 1];
            }
            if (compareColumn(strArr, i, strArr, j)) {
                j++;
            }
            prefixFunc[i] = j;
        }

        return prefixFunc;
    }

    private static boolean compareColumn(String[] strArr1, int col1, String[] strArr2, int col2) {
        for (int i = 0; i < strArr1.length; i++) {
            if (strArr1[i].charAt(col1) != strArr2[i].charAt(col2)) {
                return false;
            }
        }
        return true;
    }

    private static boolean compareColumnInMatrix(String[] strArr1, int col1, String[] strArr2, int col2, int row2) {
        for (int i = 0; i < strArr1.length; i++) {
            if (strArr1[i].charAt(col1) != strArr2[i + row2].charAt(col2)) {
                return false;
            }
        }
        return true;
    }


//    private static int countOccurrences(String[] text, String[] pattern) {
//        int countOcc = 0;
//
//        for (int i = 0; i < text.length - pattern.length + 1; i++) {
//            int fromIndex = 0;
//            while ((fromIndex = text[i].indexOf(pattern[0], fromIndex)) >= 0) {
//                if (checkOccurrence(text, pattern, i, fromIndex)) {
//                    countOcc++;
//                }
//
//                fromIndex += 1;
//            }
//
//        }
//
//        return countOcc;
//    }
//
//    private static boolean checkOccurrence(String[] text, String[] pattern, int row, int fromIndex) {
//        for (int i = 1; i < pattern.length; i++) {
//            if (!text[row + i].startsWith(pattern[i], fromIndex)) {
//                return false;
//            }
//        }
//
//        return true;
//    }
}