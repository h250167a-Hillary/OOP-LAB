public class AgeValidator {
    static void validateAge(int age) throws InvalidAgeException {   // callers MUST handle or declare it
        if (age < 0 || age > 120) throw new InvalidAgeException(age);
    }

    public static void main(String[] args) {
        int[] tests = {25, 0, 120, -5, 200};
        for (int age : tests) {
            try {
                validateAge(age);
                System.out.println(age + " is valid");
            } catch (InvalidAgeException e) {
                System.out.println("Rejected -> " + e.getMessage());
            }
        }
    }
}
