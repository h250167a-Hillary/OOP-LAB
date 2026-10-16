/**
 * Lab 2, Exercise 5: static factory methods with a private constructor.
 *
 * Why static factories can be clearer than overloaded constructors:
 * new Temperature(25.0) cannot say whether 25.0 is Celsius or Fahrenheit, and two
 * constructors taking a double would have the same signature (illegal). Factories have
 * descriptive names (fromCelsius / fromFahrenheit), can validate or cache, and can
 * return a subtype, while the private constructor keeps one canonical representation.
 */
public class Temperature {
    private final double celsius;

    private Temperature(double celsius) { this.celsius = celsius; }

    public static Temperature fromCelsius(double c) { return new Temperature(c); }
    public static Temperature fromFahrenheit(double f) { return new Temperature((f - 32) * 5 / 9); }

    public double toCelsius() { return celsius; }
    public double toFahrenheit() { return celsius * 9 / 5 + 32; }

    @Override
    public String toString() { return String.format("%.1f°C / %.1f°F", toCelsius(), toFahrenheit()); }

    public static void main(String[] args) {
        Temperature t1 = Temperature.fromCelsius(100);
        Temperature t2 = Temperature.fromFahrenheit(98.6);
        System.out.println("Boiling water : " + t1);
        System.out.println("Body temp     : " + t2);
        // new Temperature(5);  // would not compile: constructor is private
    }
}
