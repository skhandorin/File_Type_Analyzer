import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static final int A = 53;
    private static final long M = 1_000_000_000L + 9;

    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        System.out.println(countDistinctSubstrings(str));
    }

    private static int countDistinctSubstrings(String str) {
        Set<Long> setOfHashes = new HashSet<>();
        long pow = 1;
        long firstSubstrHash = 0;

        for (int len = 1; len <= str.length(); len++) {
            firstSubstrHash = (firstSubstrHash * A + charToLong(str.charAt(str.length() - len))) % M;
            long substrHash = firstSubstrHash;
            setOfHashes.add(substrHash);

            for (int i = str.length() - len - 1; i >= 0; i--) {
                substrHash = (substrHash - charToLong(str.charAt(i + len)) * pow % M + M) * A % M;
                substrHash = (charToLong(str.charAt(i)) + substrHash + M) % M;

//                String substr = str.substring(i, i + len);
//                System.out.println(substr + " - " + substrHash + " - " + calculateHash(substr));

                setOfHashes.add(substrHash);
            }

            pow = pow * A % M;
        }

        return setOfHashes.size() + 1;
    }

    private static long calculateHash(String str) {
        long hash = 0;
        long pow = 1;

        for (int i = 0; i < str.length(); i++) {
            hash = (hash + charToLong(str.charAt(i)) * pow) % M;
            pow = pow * A % M;
        }

        return hash;
    }

    private static long charToLong(char ch) {
        return ch - 'A' + 1;
    }
}