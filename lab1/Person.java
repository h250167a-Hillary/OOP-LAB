/** Lab 1, Exercise 5: a Person HAS-AN Address; chained dot and null handling. */
public class Person {
    String name;
    Address address; // reference to another object (may be null)

    public static void main(String[] args) {
        Address a = new Address();
        a.street = "12 Samora Machel Avenue";
        a.city = "Harare";

        Person p = new Person();
        p.name = "Munya";
        p.address = a;
        // chained dot expression: person -> address -> city
        System.out.println(p.name + " lives in " + p.address.city);

        // Now a person with NO address
        Person q = new Person();
        q.name = "Tatenda";
        try {
            System.out.println(q.address.city); // address is null -> NullPointerException
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: " + q.name + " has no address (address == null)");
        }
        // Safe version: test for null before using the reference
        String city = (q.address != null) ? q.address.city : "unknown";
        System.out.println(q.name + "'s city (null-safe): " + city);
    }
}
