import java.util.Arrays;

/** Lab 5, Exercise 1: method overloading (resolved at COMPILE time by parameter types). */
public class PrintDemo {
    static void print(int x)      { System.out.println("int: " + x); }
    static void print(double x)   { System.out.println("double: " + x); }
    static void print(String s)   { System.out.println("String: " + s); }
    static void print(int[] a)    { System.out.println("int[]: " + Arrays.toString(a)); }

    public static void main(String[] args) {
        print(42);
        print(3.14);
        print("hello");
        print(new int[]{1, 2, 3});
        print('A');   // char widens to int -> print(int) chosen
    }
}
