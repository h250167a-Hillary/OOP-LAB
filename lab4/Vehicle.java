/** Lab 4, Exercise 3: top of the multilevel chain Vehicle -> Car -> ElectricCar. */
public class Vehicle {
    protected String make;

    public Vehicle(String make) {
        this.make = make;
        System.out.println("1. Vehicle constructor: " + make);
    }
}
