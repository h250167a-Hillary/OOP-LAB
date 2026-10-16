/**
 * Lab 7, Exercise 5. PREDICTION: "in try", then "in finally", then "result = 1".
 * The try block evaluates the return value (1), finally runs and prints,
 * and only then does the method return 1.
 * finally ALWAYS executes (return, exception or normal end) so it is used for cleanup;
 * the only ways to skip it are System.exit() or the JVM dying.
 * Note: a return inside finally would OVERRIDE the try's return value (bad practice).
 */
public class FinallyDemo {
    static int test() {
        try {
            System.out.println("in try");
            return 1;
        } finally {
            System.out.println("in finally");
        }
    }

    @SuppressWarnings("finally")
    static int override() {
        try {
            return 1;
        } finally {
            return 2;   // overrides the try's return: avoid!
        }
    }

    public static void main(String[] args) {
        int r = test();
        System.out.println("result = " + r);
        System.out.println("override() = " + override());
    }
}
