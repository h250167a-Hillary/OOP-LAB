/** Lab 5, Exercise 3: the payroll loop never changes when new Employee types (Intern) are added. */
public class Payroll {
    public static void main(String[] args) {
        Employee[] staff = {
            new Employee("Tatenda", 3000),
            new Manager("Hillary", 3500, 800),
            new Intern("Larry", 1500),
            new Intern("Tinevimbo", 1800)
        };
        double total = 0;
        for (Employee e : staff) {            // <- this loop is unchanged by Intern
            double pay = e.calculatePay();    // dynamic dispatch
            System.out.printf("%-10s %-9s %8.2f%n", e.getName(), e.getClass().getSimpleName(), pay);
            total += pay;
        }
        System.out.printf("Total payroll: %.2f%n", total);
    }
}
