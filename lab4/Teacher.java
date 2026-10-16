/** Lab 4, Exercise 2: Teacher also extends Person, so Student and Teacher share Person code. */
public class Teacher extends Person {
    private String subject;

    public Teacher(String name, int age, String subject) {
        super(name, age);
        this.subject = subject;
    }

    public String getSubject() { return subject; }

    public static void main(String[] args) {
        Student s = new Student("Hillary", 21, "Networks");
        Teacher t = new Teacher("Mr Mujawo", 45, "Java");
        // display() is written ONCE in Person and reused by both subclasses
        s.display();
        t.display();
        System.out.println("Teacher subject: " + t.getSubject());
        System.out.println("Both are Persons? " + (s instanceof Person) + " " + (t instanceof Person));
    }
}
