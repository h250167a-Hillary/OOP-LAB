/** Lab 7, Exercise 2. */
public class ArrayAccess {
    static int safeGet(int[] arr, int index) {
        try {
            return arr[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught: " + e.getMessage());
            return -1;
        }
    }

    public static void main(String[] args) {
        int[] data = {10, 20, 30};
        System.out.println("data[1] = " + safeGet(data, 1));
        System.out.println("data[5] = " + safeGet(data, 5));
    }
}
