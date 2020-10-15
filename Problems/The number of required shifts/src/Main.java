import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        int[] array = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        System.out.println(countShifts(array));

    }

    private static int countShifts(int[] array) {
        int count = 0;
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > min) {
                count++;
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        return count;
    }
}