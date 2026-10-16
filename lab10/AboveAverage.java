import java.util.*;
import java.util.stream.*;

/** Lab 10, Exercise 4: names of students above the class average, sorted alphabetically. */
public class AboveAverage {
    record Student(String name, double mark) {}

    public static void main(String[] args) {
        List<Student> students = List.of(
            new Student("Larry", 78), new Student("Hillary", 64), new Student("Tatenda", 91),
            new Student("Munya", 55), new Student("Tinevimbo", 82), new Student("Larry", 47));
        double avg = students.stream().mapToDouble(Student::mark).average().orElse(0);
        System.out.printf("Class average: %.2f%n", avg);
        List<String> above = students.stream()
            .filter(s -> s.mark() > avg)
            .map(Student::name)
            .sorted()
            .collect(Collectors.toList());
        System.out.println("Above average: " + above);
    }
}
