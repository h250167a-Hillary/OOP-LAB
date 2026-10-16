import java.util.Arrays;

/**
 * Lab 8, Exercise 5: generic bubble sort.
 * int[] will not work because generics work only with REFERENCE types. T must be an object type
 * (type erasure replaces T by Comparable at run time), and int[] is not a T[]: primitives are not
 * objects, int[] is not an Object[], and int has no compareTo. Use Integer[] (boxing) instead.
 */
public class SortUtil {
    public static <T extends Comparable<T>> void bubbleSort(T[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    T tmp = arr[j]; arr[j] = arr[j + 1]; arr[j + 1] = tmp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public static void main(String[] args) {
        Integer[] nums = {5, 2, 9, 1, 7, 3};
        String[] words = {"pear", "apple", "mango", "banana"};
        bubbleSort(nums);
        bubbleSort(words);
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(words));
        int[] prim = {3, 1, 2};
        // bubbleSort(prim);   // compile error: inference variable T has incompatible bounds (int[] is not T[])
        System.out.println("int[] cannot be passed: " + Arrays.toString(prim) + " (use Integer[] instead)");
    }
}
