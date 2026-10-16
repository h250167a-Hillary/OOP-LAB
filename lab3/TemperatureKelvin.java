/**
 * Lab 3, Exercise 5: Temperature stored internally in KELVIN.
 * Callers only use getCelsius()/getFahrenheit(), so the internal unit is a hidden detail:
 * we could switch the field to Celsius and re-write two method bodies without breaking callers.
 */
public class TemperatureKelvin {
    private double kelvin;   // private: the representation is hidden

    public TemperatureKelvin(double celsius) { setCelsius(celsius); }

    public double getCelsius() { return kelvin - 273.15; }
    public double getFahrenheit() { return getCelsius() * 9 / 5 + 32; }

    public void setCelsius(double c) {
        double k = c + 273.15;
        if (k < 0) throw new IllegalArgumentException("Below absolute zero (-273.15 C): " + c);
        this.kelvin = k;
    }

    public static void main(String[] args) {
        TemperatureKelvin t = new TemperatureKelvin(25);
        System.out.printf("25 C   -> %.2f F%n", t.getFahrenheit());
        t.setCelsius(-40);
        System.out.printf("-40 C  -> %.2f F%n", t.getFahrenheit());
        t.setCelsius(-273.15);
        System.out.printf("Absolute zero accepted: %.2f C%n", t.getCelsius());
        try {
            t.setCelsius(-300);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        System.out.printf("Value unchanged after rejection: %.2f C%n", t.getCelsius());
    }
}
