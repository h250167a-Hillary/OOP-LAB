public class ShapeDemo {
    public static void main(String[] args) {
        // Shape s = new Shape();   // compile error: Shape is abstract
        Shape[] shapes = { new Circle(3), new Rectangle(4, 5), new Square(6) };
        for (Shape s : shapes) s.describe();
    }
}
