import java.util.ArrayList;
import java.util.List;

/** Lab 4, Exercise 5 (GOOD design): composition. A Stack HAS-A list and exposes only stack operations. */
public class GoodStack<T> {
    private final List<T> items = new ArrayList<>();   // hidden implementation detail

    public void push(T x) { items.add(x); }
    public T pop() {
        if (items.isEmpty()) throw new IllegalStateException("Stack is empty");
        return items.remove(items.size() - 1);
    }
    public T peek() { return items.get(items.size() - 1); }
    public boolean isEmpty() { return items.isEmpty(); }
    public int size() { return items.size(); }
}
