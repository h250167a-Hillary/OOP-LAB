/** Lab 1, Exercises 1 & 2: Car class with display() and isAntique(). */
public class Car {
    // State of every Car object
    String brand;
    int year;
    double mileage;

    Car(String brand, int year, double mileage) {
        this.brand = brand;
        this.year = year;
        this.mileage = mileage;
    }

    void display() {
        System.out.println("Brand: " + brand + ", Year: " + year + ", Mileage: " + mileage + " km");
    }

    // Exercise 2: a car is antique when it is more than 25 years old
    boolean isAntique() {
        int currentYear = java.time.Year.now().getValue();
        return (currentYear - year) > 25;
    }

    public static void main(String[] args) {
        Car c1 = new Car("Toyota", 1995, 250000);
        Car c2 = new Car("Honda", 2015, 98000.5);
        Car c3 = new Car("Mazda", 2022, 15000);
        Car[] cars = {c1, c2, c3};
        for (Car c : cars) {
            c.display();
            System.out.println("  Antique? " + c.isAntique());
        }
    }
}
