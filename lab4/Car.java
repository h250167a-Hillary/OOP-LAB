public class Car extends Vehicle {
    protected int doors;

    public Car(String make, int doors) {
        super(make);
        this.doors = doors;
        System.out.println("2. Car constructor: " + doors + " doors");
    }
}
