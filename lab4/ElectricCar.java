public class ElectricCar extends Car {
    private int batteryKwh;

    public ElectricCar(String make, int doors, int batteryKwh) {
        super(make, doors);
        this.batteryKwh = batteryKwh;
        System.out.println("3. ElectricCar constructor: " + batteryKwh + " kWh");
    }

    public static void main(String[] args) {
        // Constructors run from the TOP of the hierarchy down: Vehicle, Car, ElectricCar
        new ElectricCar("Nissan Leaf", 5, 40);
    }
}
