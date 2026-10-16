/** Lab 9, Exercise 2: records give constructor, accessors, equals, hashCode, toString for free. */
public class RecordDemo {
    record Student(String name, int age) {
        Student {   // compact constructor for validation
            if (age < 0) throw new IllegalArgumentException("age < 0");
        }
    }

    public static void main(String[] args) {
        Student a = new Student("Larry", 20);
        Student b = new Student("Larry", 20);
        Student c = new Student("Hillary", 22);
        System.out.println(a);                        // Student[name=Larry, age=20]
        System.out.println("a.equals(b): " + a.equals(b) + ", a == b: " + (a == b));
        System.out.println("a.equals(c): " + a.equals(c));
        System.out.println("same hashCode for equal records: " + (a.hashCode() == b.hashCode()));
        System.out.println("component name() = " + a.name() + ", age() = " + a.age());
    }
}
