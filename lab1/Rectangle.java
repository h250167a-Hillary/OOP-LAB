/** Lab 1, Exercises 3 & 4: Rectangle, largest area, and aliasing. */
public class Rectangle {
    double width;
    double height;

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    double area() { return width * height; }
    double perimeter() { return 2 * (width + height); }

    @Override
    public String toString() {
        return "Rectangle[" + width + " x " + height + ", area=" + area() + ", perimeter=" + perimeter() + "]";
    }

    public static void main(String[] args) {
        // Exercise 3: array of 5 rectangles, print the one with the largest area
        Rectangle[] rects = {
            new Rectangle(2, 3), new Rectangle(5, 4), new Rectangle(1.5, 10),
            new Rectangle(6, 6), new Rectangle(3, 2)
        };
        Rectangle largest = rects[0];
        for (Rectangle r : rects) {
            if (r.area() > largest.area()) largest = r;
        }
        System.out.println("Largest: " + largest);

        // Exercise 4: aliasing. PREDICTION: r1.width prints 99.0 because r2 and r1
        // hold the SAME reference, so changing r2.width changes the one shared object.
        Rectangle r1 = new Rectangle(4, 5);
        Rectangle r2 = r1;
        r2.width = 99;
        System.out.println("r1.width after r2.width = 99 : " + r1.width);
        System.out.println("r1 == r2 ? " + (r1 == r2));
    }
}
