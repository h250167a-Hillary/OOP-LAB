import java.util.InputMismatchException;
import java.util.Scanner;

/** Lab 7, Exercise 1: read two ints and divide, handling bad input and divide-by-zero. */
public class SafeDivide {
    static String divide(Scanner in) {
        try {
            System.out.print("Enter numerator: ");
            int a = in.nextInt();
            System.out.print("Enter denominator: ");
            int b = in.nextInt();
            return a + " / " + b + " = " + (a / b);
        } catch (InputMismatchException e) {
            in.nextLine();      // discard the bad token
            return "Error: please enter whole numbers only";
        } catch (ArithmeticException e) {
            return "Error: cannot divide by zero";
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println(divide(in));
    }
}
