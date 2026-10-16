/** Lab 7, Exercise 3: a CHECKED custom exception (extends Exception). */
public class InvalidAgeException extends Exception {
    private final int age;
    public InvalidAgeException(int age) {
        super("Invalid age: " + age + " (must be 0 to 120)");
        this.age = age;
    }
    public int getAge() { return age; }
}
