import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/** One active loan of one item to one member. Immutable once created. */
public class Loan {
    private final LibraryItem item;
    private final Member member;
    private final LocalDate loanDate;
    private final LocalDate dueDate;

    public Loan(LibraryItem item, Member member, LocalDate loanDate) {
        if (item == null || member == null || loanDate == null)
            throw new IllegalArgumentException("Loan needs an item, a member and a date");
        this.item = item;
        this.member = member;
        this.loanDate = loanDate;
        this.dueDate = loanDate.plusDays(item.loanPeriodDays());   // polymorphic call
    }

    public LibraryItem getItem() { return item; }
    public Member getMember() { return member; }
    public LocalDate getLoanDate() { return loanDate; }
    public LocalDate getDueDate() { return dueDate; }

    public boolean isOverdue(LocalDate today) { return today.isAfter(dueDate); }
    public long daysOverdue(LocalDate today) { return Math.max(0, ChronoUnit.DAYS.between(dueDate, today)); }

    @Override
    public String toString() {
        return String.format("%s -> %s (borrowed %s, due %s)", item.getTitle(), member.getName(), loanDate, dueDate);
    }
}
