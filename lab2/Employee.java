/** Lab 2, Exercise 3: auto-incrementing id, overloaded and copy constructors. */
public class Employee {
    private static int nextId = 1000;   // static counter shared by all employees
    private final int employeeId;
    private String name;
    private String department;
    private double salary;

    public Employee(String name, String department, double salary) {
        this.employeeId = nextId++;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public Employee(String name, String department) {
        this(name, department, 0.0);
    }

    public Employee(String name) {
        this(name, "Unassigned");
    }

    // Copy constructor: new id (a different employee record) but same details
    public Employee(Employee other) {
        this(other.name, other.department, other.salary);
    }

    @Override
    public String toString() {
        return String.format("Employee #%d %s, %s, $%.2f", employeeId, name, department, salary);
    }

    public static void main(String[] args) {
        Employee e1 = new Employee("Larry", "IT", 3500);
        Employee e2 = new Employee("Hillary", "HR");
        Employee e3 = new Employee("Tinevimbo");
        Employee e4 = new Employee(e1); // copy of e1
        System.out.println(e1);
        System.out.println(e2);
        System.out.println(e3);
        System.out.println(e4 + "   (copy of e1, new id)");
        System.out.println("e1 == e4 ? " + (e1 == e4));
    }
}
