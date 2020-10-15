import java.util.Scanner;

public class Main {

    private static final int CONST_A = 53;
    private static final long CONST_M = 1_000_000_000L + 9;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String str = scanner.nextLine();
        long[] prefixHashes = calculatePrefixHashes(str);

        int k = scanner.nextInt();
        scanner.nextLine();
        long[] substringHashes = new long[k];

        for (int l = 0; l < k; l++) {
            int i = scanner.nextInt();
            int j = scanner.nextInt();
            scanner.nextLine();

            substringHashes[l] = (prefixHashes[j] - prefixHashes[i] + CONST_M) % CONST_M;
        }

        for (int i = 1; i < prefixHashes.length; i++) {
            System.out.print(prefixHashes[i] + (i == (prefixHashes.length - 1) ? "\n" : " "));
        }
        for (int i = 0; i < substringHashes.length; i++) {
            System.out.print(substringHashes[i] + (i == (substringHashes.length - 1) ? "\n" : " "));
        }
//        Arrays.stream(prefixHashes).forEach(value -> System.out.print(value + " "));
//        System.out.println();
//        Arrays.stream(substringHashes).forEach(value -> System.out.print(value + " "));
//        System.out.println();

    }

    private static long[] calculatePrefixHashes(String str) {
        long[] prefixHashes = new long[str.length() + 1];
        long currPrefixHash = 0;
        long pow = 1;


        for (int i = 0; i < str.length(); i++) {
            currPrefixHash = (currPrefixHash + charToLong(str.charAt(i)) * pow) % CONST_M;
            prefixHashes[i + 1] = currPrefixHash;
            pow = pow * CONST_A % CONST_M;      // for the next iteration
        }

        return prefixHashes;
    }

    private static long charToLong(char ch) {
        return (long) (ch - 'A' + 1);
    }
}