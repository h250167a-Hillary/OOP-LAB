/** Checked exception: the requested item is already on loan (Requirement 4). */
public class ItemNotAvailableException extends Exception {
    public ItemNotAvailableException(String itemId, String title) {
        super("Item " + itemId + " (\"" + title + "\") is not available: it is already on loan");
    }
}
