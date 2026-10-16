import java.io.IOException;
import java.nio.file.*;
import java.time.Clock;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Core service. Items and members live in Map<String, ...> keyed by id (Requirement 5).
 * Data is saved to / loaded from three CSV files (Requirement 6).
 */
public class Library {
    private final Map<String, LibraryItem> items = new LinkedHashMap<>();
    private final Map<String, Member> members = new LinkedHashMap<>();
    private final Clock clock;

    public Library() { this(Clock.systemDefaultZone()); }
    public Library(Clock clock) { this.clock = clock; }   // injectable so tests can fix "today"

    public LocalDate today() { return LocalDate.now(clock); }

    // ---------- adding and looking up ----------
    public void addItem(LibraryItem item) {
        if (items.containsKey(item.getId()))
            throw new IllegalArgumentException("An item with id " + item.getId() + " already exists");
        items.put(item.getId(), item);
    }

    public void registerMember(Member member) {
        if (members.containsKey(member.getId()))
            throw new IllegalArgumentException("A member with id " + member.getId() + " already exists");
        members.put(member.getId(), member);
    }

    public LibraryItem getItem(String id) { return items.get(id); }
    public Member getMember(String id) { return members.get(id); }
    public Collection<LibraryItem> allItems() { return Collections.unmodifiableCollection(items.values()); }
    public Collection<Member> allMembers() { return Collections.unmodifiableCollection(members.values()); }

    // ---------- borrowing and returning ----------
    public Loan borrow(String memberId, String itemId)
            throws ItemNotAvailableException, MemberLimitExceededException {
        return borrow(memberId, itemId, today());
    }

    Loan borrow(String memberId, String itemId, LocalDate date)
            throws ItemNotAvailableException, MemberLimitExceededException {
        // 1. look up member and item
        Member member = members.get(memberId);
        if (member == null) throw new IllegalArgumentException("No such member: " + memberId);
        LibraryItem item = items.get(itemId);
        if (item == null) throw new IllegalArgumentException("No such item: " + itemId);
        // 2. check availability and the loan limit BEFORE changing anything, so a failure leaves no half-done state
        if (!item.isAvailable()) throw new ItemNotAvailableException(item.getId(), item.getTitle());
        if (!member.canBorrow()) throw new MemberLimitExceededException(member.getId(), Member.MAX_LOANS);
        // 3. create the loan, mark the item unavailable, record it on the member
        Loan loan = new Loan(item, member, date);
        item.borrow(member);
        member.addLoan(loan);
        return loan;
    }

    /** Returns the item; result is the number of days it was overdue (0 if on time). */
    public long giveBack(String itemId) {
        LibraryItem item = items.get(itemId);
        if (item == null) throw new IllegalArgumentException("No such item: " + itemId);
        if (item.isAvailable()) throw new IllegalStateException("Item " + itemId + " is not on loan");
        Loan loan = findLoan(itemId).orElseThrow(() -> new IllegalStateException("No loan record for " + itemId));
        loan.getMember().removeLoan(loan);
        item.giveBack();
        return loan.daysOverdue(today());
    }

    private Optional<Loan> findLoan(String itemId) {
        return activeLoans().stream().filter(l -> l.getItem().getId().equals(itemId)).findFirst();
    }

    public List<Loan> activeLoans() {
        return members.values().stream().flatMap(m -> m.getLoans().stream()).collect(Collectors.toList());
    }

    // ---------- streams ----------
    public List<Loan> overdueLoans() {
        LocalDate today = today();
        return activeLoans().stream()
            .filter(l -> l.isOverdue(today))
            .sorted(Comparator.comparing(Loan::getDueDate))
            .toList();
    }

    public List<LibraryItem> searchByTitle(String text) {
        return items.values().stream()
            .filter(i -> i.getTitle().toLowerCase().contains(text.toLowerCase()))
            .sorted(Comparator.comparing(LibraryItem::getTitle))
            .toList();
    }

    // ---------- persistence ----------
    private static final String ITEMS = "items.csv", MEMBERS = "members.csv", LOANS = "loans.csv";

    public void save(Path dir) throws IOException {
        Files.createDirectories(dir);
        List<String> itemLines = new ArrayList<>(List.of("type,id,title,extra"));
        items.values().forEach(i -> itemLines.add(Csv.join(i.type(), i.getId(), i.getTitle(), i.extraField())));
        Files.write(dir.resolve(ITEMS), itemLines);

        List<String> memberLines = new ArrayList<>(List.of("id,name"));
        members.values().forEach(m -> memberLines.add(Csv.join(m.getId(), m.getName())));
        Files.write(dir.resolve(MEMBERS), memberLines);

        List<String> loanLines = new ArrayList<>(List.of("itemId,memberId,loanDate"));
        activeLoans().forEach(l -> loanLines.add(
            Csv.join(l.getItem().getId(), l.getMember().getId(), l.getLoanDate().toString())));
        Files.write(dir.resolve(LOANS), loanLines);
    }

    /** Loads whatever files exist. Missing files simply mean an empty library (first run). */
    public void load(Path dir) throws IOException {
        items.clear();
        members.clear();
        for (List<String> f : readCsv(dir.resolve(ITEMS))) {
            switch (f.get(0)) {
                case "BOOK" -> addItem(new Book(f.get(1), f.get(2), f.get(3)));
                case "DVD"  -> addItem(new Dvd(f.get(1), f.get(2), Integer.parseInt(f.get(3))));
                default     -> throw new IOException("Unknown item type in " + ITEMS + ": " + f.get(0));
            }
        }
        for (List<String> f : readCsv(dir.resolve(MEMBERS))) registerMember(new Member(f.get(0), f.get(1)));
        for (List<String> f : readCsv(dir.resolve(LOANS))) {
            try {
                borrow(f.get(1), f.get(0), LocalDate.parse(f.get(2)));    // rebuilds item/member state
            } catch (ItemNotAvailableException | MemberLimitExceededException e) {
                throw new IOException("Corrupt loans file: " + e.getMessage(), e);
            }
        }
    }

    private static List<List<String>> readCsv(Path file) throws IOException {
        if (!Files.exists(file)) return List.of();
        try (Stream<String> lines = Files.lines(file)) {
            return lines.skip(1).filter(l -> !l.isBlank()).map(Csv::parse).toList();   // skip header row
        }
    }
}
