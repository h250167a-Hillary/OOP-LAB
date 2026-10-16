/** Lab 2, Exercises 1 & 2: constructors and a static instance counter. */
public class Circle {
    private static int count = 0;      // one copy shared by all circles
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
        count++;
    }

    public Circle() {
        this(1.0); // constructor chaining: default radius 1.0
    }

    public double area() { return Math.PI * radius * radius; }
    public static int getCount() { return count; }

    @Override
    public String toString() { return String.format("Circle[r=%.1f, area=%.2f]", radius, area()); }

    public static void main(String[] args) {
        System.out.println("Circles before: " + Circle.getCount());
        Circle c1 = new Circle(2.5);
        Circle c2 = new Circle();
        Circle c3 = new Circle(4);
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println("Circles created: " + Circle.getCount());
    }
}
