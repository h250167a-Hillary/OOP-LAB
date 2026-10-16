/** Intern receives a fixed stipend: 60% of base salary. */
public class Intern extends Employee {
    public Intern(String name, double baseSalary) { super(name, baseSalary); }
    @Override public double calculatePay() { return baseSalary * 0.6; }
}
