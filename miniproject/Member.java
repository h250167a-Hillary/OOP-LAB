import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** A library member. Holds at most MAX_LOANS active loans. */
public class Member {
    public static final int MAX_LOANS = 3;

    private final String id;
    private String name;
    private final List<Loan> loans = new ArrayList<>();

    public Member(String id, String name) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Member id is required");
        this.id = id.trim();
        setName(name);
    }

    public String getId() { return id; }
    public String getName() { return name; }

    public void setName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Member name is required");
        this.name = name.trim();
    }

    /** Read-only view so outside code cannot bypass the loan limit. */
    public List<Loan> getLoans() { return Collections.unmodifiableList(loans); }

    public int loanCount() { return loans.size(); }
    public boolean canBorrow() { return loans.size() < MAX_LOANS; }

    void addLoan(Loan loan) throws MemberLimitExceededException {
        if (!canBorrow()) throw new MemberLimitExceededException(id, MAX_LOANS);
        loans.add(loan);
    }

    boolean removeLoan(Loan loan) { return loans.remove(loan); }

    @Override
    public String toString() { return String.format("%-6s %-20s loans: %d/%d", id, name, loans.size(), MAX_LOANS); }
}
