import java.util.ArrayList;
import java.util.List;

/** Lab 8, Exercise 3: generic Stack<T> backed by an ArrayList<T>. */
public class MyStack<T> {
    private final List<T> items = new ArrayList<>();

    public void push(T item) { items.add(item); }

    public T pop() {
        if (isEmpty()) throw new IllegalStateException("Stack is empty");
        return items.remove(items.size() - 1);
    }

    public T peek() {
        if (isEmpty()) throw new IllegalStateException("Stack is empty");
        return items.get(items.size() - 1);
    }

    public boolean isEmpty() { return items.isEmpty(); }
    public int size() { return items.size(); }

    public static void main(String[] args) {
        MyStack<Integer> ints = new MyStack<>();
        ints.push(1); ints.push(2); ints.push(3);
        System.out.println("peek = " + ints.peek() + ", size = " + ints.size());
        while (!ints.isEmpty()) System.out.print(ints.pop() + " ");
        System.out.println();

        MyStack<String> words = new MyStack<>();
        words.push("alpha"); words.push("beta");
        System.out.println("String stack pop: " + words.pop());
        // words.push(5);   // compile error: type safety from generics
        try { new MyStack<Double>().pop(); }
        catch (IllegalStateException e) { System.out.println("Empty pop -> " + e.getMessage()); }
    }
}
