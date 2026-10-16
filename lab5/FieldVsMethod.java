/**
 * Lab 5, Exercise 4: methods are polymorphic, fields (and static methods) are NOT.
 * Methods are bound at RUN time to the real object's class (dynamic dispatch).
 * Fields are bound at COMPILE time to the declared (reference) type, so a superclass
 * reference reads the superclass's field even if the object is a subclass (field hiding).
 */
public class FieldVsMethod {
    static class Parent {
        String label = "Parent field";
        String who() { return "Parent method"; }
        static String stat() { return "Parent static"; }
    }
    static class Child extends Parent {
        String label = "Child field";            // hides, does not override
        @Override String who() { return "Child method"; }
        static String stat() { return "Child static"; }   // hides, does not override
    }

    public static void main(String[] args) {
        Parent p = new Child();
        System.out.println("p.who()   -> " + p.who());     // Child method (dynamic)
        System.out.println("p.label   -> " + p.label);     // Parent field (static binding)
        System.out.println("((Child) p).label -> " + ((Child) p).label); // Child field
        System.out.println("p.stat()  -> " + Parent.stat() + "  (static methods bind to the declared type)");
    }
}
