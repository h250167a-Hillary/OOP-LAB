/** Lab 6, Exercise 1: abstract Shape. */
public abstract class Shape {
    public abstract double area();
    public abstract double perimeter();

    // concrete method shared by all shapes
    public void describe() {
        System.out.printf("%-9s area=%8.2f perimeter=%7.2f%n", getClass().getSimpleName(), area(), perimeter());
    }
}
