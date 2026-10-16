import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/** Console menu for the Library Management System (Requirement 7). */
public class LibraryApp {
    private final Library library = new Library();
    private final Scanner in;
    private final Path dataDir;

    public LibraryApp(Scanner in, Path dataDir) {
        this.in = in;
        this.dataDir = dataDir;
    }

    public static void main(String[] args) {
        Path dir = Path.of(args.length > 0 ? args[0] : "data");
        new LibraryApp(new Scanner(System.in), dir).run();
    }

    public void run() {
        try {
            library.load(dir());
            System.out.println("Loaded " + library.allItems().size() + " items, "
                + library.allMembers().size() + " members, " + library.activeLoans().size() + " loans from " + dir());
        } catch (IOException | RuntimeException e) {
            System.out.println("Could not load saved data (" + e.getMessage() + "). Starting empty.");
        }
        boolean running = true;
        while (running) {
            printMenu();
            String choice = prompt("Choose an option: ");
            if (choice == null) choice = "0";       // end of input: save and leave
            try {
                switch (choice.trim()) {
                    case "1" -> addItem();
                    case "2" -> registerMember();
                    case "3" -> borrow();
                    case "4" -> giveBack();
                    case "5" -> listOverdue();
                    case "6" -> search();
                    case "7" -> library.allItems().forEach(System.out::println);
                    case "8" -> library.allMembers().forEach(System.out::println);
                    case "0" -> running = false;
                    default -> System.out.println("Unknown option, try again.");
                }
            } catch (ItemNotAvailableException | MemberLimitExceededException e) {
                System.out.println("Cannot complete: " + e.getMessage());
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        try {
            library.save(dir());
            System.out.println("Data saved to " + dir() + ". Goodbye!");
        } catch (IOException e) {
            System.out.println("WARNING: could not save data: " + e.getMessage());
        }
    }

    private Path dir() { return dataDir; }

    private void printMenu() {
        System.out.println("""

            ===== Library Management System =====
            1. Add item          5. List overdue items
            2. Register member   6. Search by title
            3. Borrow            7. List all items
            4. Return            8. List members
            0. Save and exit""");
    }

    private String prompt(String label) {
        System.out.print(label);
        if (!in.hasNextLine()) return null;
        String line = in.nextLine();
        if (System.console() == null) System.out.println(line);   // echo piped input so transcripts are readable
        return line;
    }

    private String ask(String label) {
        String s = prompt(label);
        if (s == null) throw new IllegalStateException("Input ended");
        return s.trim();
    }

    private void addItem() {
        String type = ask("Type (B = book, D = dvd): ").toUpperCase();
        String id = ask("Item id: ");
        String title = ask("Title: ");
        LibraryItem item;
        if (type.equals("B")) {
            item = new Book(id, title, ask("Author: "));
        } else if (type.equals("D")) {
            item = new Dvd(id, title, parseInt(ask("Duration (minutes): ")));
        } else {
            throw new IllegalArgumentException("Type must be B or D");
        }
        library.addItem(item);
        System.out.println("Added: " + item);
    }

    private void registerMember() {
        Member m = new Member(ask("Member id: "), ask("Name: "));
        library.registerMember(m);
        System.out.println("Registered: " + m);
    }

    private void borrow() throws ItemNotAvailableException, MemberLimitExceededException {
        Loan loan = library.borrow(ask("Member id: "), ask("Item id: "));
        System.out.println("Loan created: " + loan);
    }

    private void giveBack() {
        String itemId = ask("Item id to return: ");
        long late = library.giveBack(itemId);
        System.out.println(late == 0 ? "Returned on time. Thank you!"
            : "Returned " + late + " day(s) late.");
    }

    private void listOverdue() {
        List<Loan> overdue = library.overdueLoans();
        LocalDate today = library.today();
        if (overdue.isEmpty()) System.out.println("No overdue items.");
        else overdue.forEach(l -> System.out.println(l + "  -> " + l.daysOverdue(today) + " day(s) overdue"));
    }

    private void search() {
        List<LibraryItem> found = library.searchByTitle(ask("Search text: "));
        if (found.isEmpty()) System.out.println("No matches.");
        else found.forEach(System.out::println);
    }

    private static int parseInt(String s) {
        try { return Integer.parseInt(s); }
        catch (NumberFormatException e) { throw new IllegalArgumentException("'" + s + "' is not a whole number"); }
    }
}
