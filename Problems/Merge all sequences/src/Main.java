import java.util.Arrays;
import java.util.Scanner;

class Main {
    static int sortingOrder = 0;

    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner((System.in));
        int n = Integer.parseInt(scanner.nextLine());

        int[][] arrays = new int[n][];

        for (int i = 0; i < n; i++) {
            int m = scanner.nextInt();
            //arrays[i] = new int[m];
            arrays[i] = Arrays.stream(scanner.nextLine().trim().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            if (sortingOrder == 0 && arrays[i].length > 1) {
                sortingOrder = Integer.compare(arrays[i][0], arrays[i][1]);
            }
        }

        int[] resultArr = new int[0];
        for (int i = 0; i < arrays.length; i++) {
            resultArr = mergeArrays(resultArr, arrays[i]);
        }

        Arrays.stream(resultArr).forEach(i -> System.out.print(i + " "));
    }

    public static int[] mergeArrays(int[] array1, int[] array2) {
        int[] result = new int[array1.length + array2.length];

        int i1 = 0;
        int i2 = 0;

        for (int i = 0; i < result.length; i++) {
            if (i1 == array1.length) {
                result[i] = array2[i2++];
            } else if (i2 == array2.length || Integer.compare(array1[i1], array2[i2]) == sortingOrder) {
                result[i] = array1[i1++];
            } else {        // array1[i1] > array2[i2]
                result[i] = array2[i2++];
            }
        }

        return result;
    }

}