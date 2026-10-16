import java.util.ArrayList;

/**
 * Lab 4, Exercise 5 (BAD design): Stack extends ArrayList.
 * It WRONGLY exposes every ArrayList method: add(index, x), remove(index), get(i), set...
 * so a caller can break the LIFO rule.
 */
public class BadStack<T> extends ArrayList<T> {
    public void push(T x) { add(x); }
    public T pop() { return remove(size() - 1); }
}
