import java.nio.file.*;
import java.time.*;
import java.util.*;

/** Plain-Java test runner (no JUnit needed). Exit code 1 if any test fails. */
public class LibraryTest {
    static int passed = 0, failed = 0;

    static void check(String name, boolean ok) {
        if (ok) { passed++; System.out.println("PASS  " + name); }
        else    { failed++; System.out.println("FAIL  " + name); }
    }

    static Library fresh(LocalDate today) {
        Clock fixed = Clock.fixed(today.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        Library lib = new Library(fixed);
        lib.addItem(new Book("B1", "Clean Code", "Robert Martin"));
        lib.addItem(new Book("B2", "Effective Java", "Joshua Bloch"));
        lib.addItem(new Book("B3", "Java Concurrency", "Brian Goetz"));
        lib.addItem(new Dvd("D1", "The Matrix", 136));
        lib.addItem(new Book("B5", "Clean Architecture", "Robert Martin"));
        lib.registerMember(new Member("M1", "Larry"));
        lib.registerMember(new Member("M2", "Hillary"));
        return lib;
    }

    public static void main(String[] args) throws Exception {
        LocalDate day = LocalDate.of(2026, 10, 1);

        // polymorphism / abstraction
        Library lib = fresh(day);
        check("Book loan period is 14", lib.getItem("B1").loanPeriodDays() == 14);
        check("Dvd loan period is 7", lib.getItem("D1").loanPeriodDays() == 7);

        // borrow and availability
        Loan loan = lib.borrow("M1", "D1");
        check("DVD due date = loan date + 7", loan.getDueDate().equals(day.plusDays(7)));
        check("Borrowed item becomes unavailable", !lib.getItem("D1").isAvailable());
        check("Member holds 1 loan", lib.getMember("M1").loanCount() == 1);

        // ItemNotAvailableException
        try { lib.borrow("M2", "D1"); check("ItemNotAvailableException thrown", false); }
        catch (ItemNotAvailableException e) { check("ItemNotAvailableException thrown", true); }

        // MemberLimitExceededException (max 3)
        lib.borrow("M1", "B1");
        lib.borrow("M1", "B2");
        try { lib.borrow("M1", "B3"); check("MemberLimitExceededException thrown on 4th loan", false); }
        catch (MemberLimitExceededException e) { check("MemberLimitExceededException thrown on 4th loan", true); }
        check("Failed borrow left item available", lib.getItem("B3").isAvailable());
        check("Failed borrow left member at 3 loans", lib.getMember("M1").loanCount() == 3);

        // unknown ids
        try { lib.borrow("NOPE", "B3"); check("Unknown member rejected", false); }
        catch (IllegalArgumentException e) { check("Unknown member rejected", true); }
        try { lib.borrow("M2", "NOPE"); check("Unknown item rejected", false); }
        catch (IllegalArgumentException e) { check("Unknown item rejected", true); }

        // return
        long late = lib.giveBack("D1");
        check("Return on time reports 0 days late", late == 0);
        check("Returned item available again", lib.getItem("D1").isAvailable());
        check("Member loan count drops to 2", lib.getMember("M1").loanCount() == 2);

        // validation
        try { new Member("", "x"); check("Blank member id rejected", false); }
        catch (IllegalArgumentException e) { check("Blank member id rejected", true); }
        try { new Dvd("D9", "Bad", 0); check("Zero-minute DVD rejected", false); }
        catch (IllegalArgumentException e) { check("Zero-minute DVD rejected", true); }
        try { lib.addItem(new Book("B1", "Dup", "X")); check("Duplicate item id rejected", false); }
        catch (IllegalArgumentException e) { check("Duplicate item id rejected", true); }

        // search with streams
        List<LibraryItem> found = lib.searchByTitle("clean");
        check("Search is case-insensitive and finds both 'clean' titles", found.size() == 2);
        check("Search results sorted by title",
              found.get(0).getTitle().equals("Clean Architecture") && found.get(1).getTitle().equals("Clean Code"));

        // overdue (clock is moved by building a second library with a later date and the same data)
        Path dir = Files.createTempDirectory("libtest");
        lib.save(dir);
        Library later = new Library(Clock.fixed(LocalDate.of(2026, 10, 20).atStartOfDay(ZoneId.systemDefault()).toInstant(),
                                                ZoneId.systemDefault()));
        later.load(dir);
        check("Persistence: items reloaded", later.allItems().size() == 5);
        check("Persistence: members reloaded", later.allMembers().size() == 2);
        check("Persistence: loans reloaded", later.activeLoans().size() == 2);
        check("Persistence: on-loan item still unavailable", !later.getItem("B1").isAvailable());
        check("Persistence: loan date preserved", later.getMember("M1").getLoans().get(0).getLoanDate().equals(day));
        check("Overdue on 20 Oct: both books (due 15 Oct) are overdue", later.overdueLoans().size() == 2);
        check("Overdue days = 5", later.overdueLoans().get(0).daysOverdue(later.today()) == 5);
        check("Late return reports 5 days", later.giveBack("B1") == 5);

        // CSV with commas and quotes in a title
        Library tricky = fresh(day);
        tricky.addItem(new Book("B9", "Hello, \"World\"", "A, B"));
        Path d2 = Files.createTempDirectory("libtest2");
        tricky.save(d2);
        Library reloaded = new Library();
        reloaded.load(d2);
        check("CSV round-trip keeps commas and quotes", reloaded.getItem("B9").getTitle().equals("Hello, \"World\""));

        System.out.println("\n" + passed + " passed, " + failed + " failed");
        System.exit(failed == 0 ? 0 : 1);
    }
}
