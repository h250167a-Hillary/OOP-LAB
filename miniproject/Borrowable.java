/** Contract for anything that can be lent to a Member (Requirement 3). */
public interface Borrowable {
    /** Marks the item as lent to the member. @throws ItemNotAvailableException if already on loan. */
    void borrow(Member member) throws ItemNotAvailableException;

    /** Marks the item as back on the shelf. */
    void giveBack();
}
