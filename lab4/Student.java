/** Lab 4, Exercise 1: Student extends Person (adds course). */
public class Student extends Person {
    private String course;

    public Student(String name, int age, String course) {
        super(name, age);          // must call the Person constructor first
        this.course = course;
    }

    public String getCourse() { return course; }

    public static void main(String[] args) {
        Person p = new Person("Munya", 40);
        Student s = new Student("Larry", 20, "Software Engineering");
        p.display();
        s.display();   // inherited display(): prints only the Person part
        System.out.println("Course: " + s.getCourse());
    }
}
