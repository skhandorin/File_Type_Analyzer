import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static final int A = 53;
    private static final long M = 1_000_000_000L + 9;

    private static long[] powArray;
    private static long[] prefixHashes;

    public static void main(String[] args) throws IOException {
        // put your code here
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


//        Scanner scanner = new Scanner(System.in);
        String str = reader.readLine();
        calculatePrefixHashes(str);

        int t = Integer.parseInt(reader.readLine());
        int numberOfEqualPairs = 0;

        for (int i = 0; i < t; i++) {
            String line = reader.readLine();
            int[] numbers = Arrays.stream(line.split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
//            Scanner scanner = new Scanner(line);
//            int start1 = scanner.nextInt();
//            int end1 = scanner.nextInt();
//            int start2 = scanner.nextInt();
//            int end2 = scanner.nextInt();
//            scanner.nextLine();

            if (compareSubstrings(numbers[0], numbers[1], numbers[2], numbers[3])) {
                numberOfEqualPairs++;
            }
        }

        System.out.println(numberOfEqualPairs);

    }

    private static boolean compareSubstrings(int start1, int end1, int start2, int end2) {
        long hash1 = (prefixHashes[end1] - prefixHashes[start1] + M) % M;
        long hash2 = (prefixHashes[end2] - prefixHashes[start2] + M) % M;

        if (start1 < start2) {
            return hash1 * powArray[start2 - start1] % M == hash2;
        } else {
            return hash2 * powArray[start1 - start2] % M == hash1;
        }
    }

    private static void calculatePrefixHashes(String str) {
        prefixHashes = new long[str.length() + 1];
        prefixHashes[0] = 0L;

        powArray = new long[str.length() + 1];
        powArray[0] = 1L;

        for (int i = 1; i <= str.length(); i++) {
            prefixHashes[i] = (prefixHashes[i - 1] + charToLong(str.charAt(i - 1)) * powArray[i - 1]) % M;
            powArray[i] = powArray[i - 1] * A % M;      // for the next iteration
        }

    }

    private static long charToLong(char ch) {
        return ch - 'A' + 1;
    }
}