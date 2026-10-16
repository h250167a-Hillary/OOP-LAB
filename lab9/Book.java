import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/** Lab 9, Exercise 3: equality by ISBN; a HashSet then rejects duplicates. */
public class Book {
    private final String isbn;
    private final String title;

    public Book(String isbn, String title) { this.isbn = isbn; this.title = title; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book other)) return false;
        return isbn.equals(other.isbn);
    }
    // equal objects MUST have equal hash codes, otherwise HashSet looks in the wrong bucket
    @Override public int hashCode() { return Objects.hash(isbn); }
    @Override public String toString() { return title + " [" + isbn + "]"; }

    public static void main(String[] args) {
        Set<Book> set = new HashSet<>();
        System.out.println("add Clean Code            -> " + set.add(new Book("978-0132350884", "Clean Code")));
        System.out.println("add Effective Java        -> " + set.add(new Book("978-0134685991", "Effective Java")));
        System.out.println("add Clean Code (same ISBN)-> " + set.add(new Book("978-0132350884", "Clean Code (2nd copy)")));
        System.out.println("Set size = " + set.size() + " -> " + set);
    }
}
