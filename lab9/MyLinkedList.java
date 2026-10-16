import java.util.Iterator;
import java.util.NoSuchElementException;

/** Lab 9, Exercise 4: singly linked list with a private static nested Node and an inner Iterator. */
public class MyLinkedList<T> implements Iterable<T> {
    // static nested: a Node needs no reference to the list that owns it
    private static class Node<T> {
        T data;
        Node<T> next;
        Node(T data) { this.data = data; }
    }

    private Node<T> head, tail;
    private int size;

    public void add(T item) {
        Node<T> n = new Node<>(item);
        if (head == null) head = tail = n;
        else { tail.next = n; tail = n; }
        size++;
    }

    public int size() { return size; }

    // inner (non-static) class: it uses the enclosing list's 'head'
    private class ListIterator implements Iterator<T> {
        private Node<T> current = head;
        @Override public boolean hasNext() { return current != null; }
        @Override public T next() {
            if (current == null) throw new NoSuchElementException();
            T value = current.data;
            current = current.next;
            return value;
        }
    }

    @Override public Iterator<T> iterator() { return new ListIterator(); }

    public static void main(String[] args) {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("one"); list.add("two"); list.add("three");
        for (String s : list) System.out.println(s);   // works because we implement Iterable
        System.out.println("size = " + list.size());
    }
}
