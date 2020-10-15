import java.util.*;

public class Main {
    static int mergeCount = 0;

    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);

        scanner.nextLine();
        int[] arr = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        mergeSortTopDown(arr);

        System.out.println(mergeCount);
    }

    public static int[] mergeArrays(int[] array1, int[] array2) {
        mergeCount++;

        int[] result = new int[array1.length + array2.length];

        int i1 = 0;
        int i2 = 0;

        for (int i = 0; i < result.length; i++) {
            if (i1 == array1.length) {
                result[i] = array2[i2++];
            } else if (i2 == array2.length || array1[i1] <= array2[i2]) {
                result[i] = array1[i1++];
            } else {        // array1[i1] > array2[i2]
                result[i] = array2[i2++];
            }
        }

        return result;
    }

    public static int[] mergeSortTopDown(int[] array) {
        if (array.length == 1) {
            return array;
        }

        int middleIndex = (array.length + 1) / 2 - 1;

        int[] leftArray = mergeSortTopDown(Arrays.copyOfRange(array, 0, middleIndex + 1));
        int[] rightArray = mergeSortTopDown(Arrays.copyOfRange(array, middleIndex + 1, array.length));

        return mergeArrays(leftArray, rightArray);
    }

}