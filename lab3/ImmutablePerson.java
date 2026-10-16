import java.time.LocalDate;

/**
 * Lab 3, Exercise 4: an immutable class.
 * Design choices:
 *  - final class      : no subclass can add mutable state or override getters to cheat.
 *  - private final fields : each field is assigned once, in the constructor, and is never exposed.
 *  - no setters       : state cannot change after construction, so the object is thread-safe
 *                       and safe to share or use as a map key.
 *  - validation in constructor : an invalid object can never exist.
 *  - mutable components (LocalDate here is itself immutable) would have to be defensively
 *    copied on the way in and out. "Changes" are expressed by returning a NEW object (withName).
 */
public final class ImmutablePerson {
    private final String name;
    private final LocalDate dateOfBirth;

    public ImmutablePerson(String name, LocalDate dateOfBirth) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name required");
        if (dateOfBirth == null || dateOfBirth.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Invalid date of birth");
        this.name = name.trim();
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() { return name; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }

    public ImmutablePerson withName(String newName) {
        return new ImmutablePerson(newName, dateOfBirth);
    }

    @Override
    public String toString() { return name + " (born " + dateOfBirth + ")"; }

    public static void main(String[] args) {
        ImmutablePerson p = new ImmutablePerson("Tinevimbo", LocalDate.of(2001, 5, 17));
        ImmutablePerson q = p.withName("Tinevimbo Haruzivi");
        System.out.println("Original : " + p);
        System.out.println("Modified : " + q + "   (a new object; original unchanged)");
    }
}
