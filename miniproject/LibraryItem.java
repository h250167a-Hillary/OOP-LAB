/**
 * Abstract base class for everything the library lends (Requirement 2).
 * Encapsulation: fields are private; subclasses define their own loan period.
 */
public abstract class LibraryItem implements Borrowable {
    private final String id;
    private final String title;
    private boolean available = true;

    protected LibraryItem(String id, String title) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Item id is required");
        if (title == null || title.isBlank()) throw new IllegalArgumentException("Item title is required");
        this.id = id.trim();
        this.title = title.trim();
    }

    /** Each subclass decides how many days it may be borrowed (abstraction + polymorphism). */
    public abstract int loanPeriodDays();

    /** Short type label used in listings and in the CSV file. */
    public abstract String type();

    /** The one extra, subclass-specific CSV value (author or duration). */
    public abstract String extraField();

    public String getId() { return id; }
    public String getTitle() { return title; }
    public boolean isAvailable() { return available; }

    @Override
    public void borrow(Member member) throws ItemNotAvailableException {
        if (!available) throw new ItemNotAvailableException(id, title);
        available = false;
    }

    @Override
    public void giveBack() { available = true; }

    @Override
    public String toString() {
        return String.format("[%s] %-6s %-30s loan:%2d days  %s",
            type(), id, title, loanPeriodDays(), available ? "available" : "ON LOAN");
    }
}
