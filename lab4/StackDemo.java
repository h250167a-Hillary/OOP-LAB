public class StackDemo {
    public static void main(String[] args) {
        BadStack<String> bad = new BadStack<>();
        bad.push("A"); bad.push("B"); bad.push("C");
        bad.add(0, "SNEAKY");   // inserts at the BOTTOM: violates LIFO, allowed by inheritance
        bad.remove(1);          // removes from the middle: also allowed
        System.out.println("BadStack contents (LIFO broken): " + bad);

        GoodStack<String> good = new GoodStack<>();
        good.push("A"); good.push("B"); good.push("C");
        // good.add(0, "SNEAKY");  // does not compile: no such method
        System.out.println("GoodStack pop order: " + good.pop() + good.pop() + good.pop());
        System.out.println(
            "Inheritance wrongly exposes add(index), remove(index), get, set, clear, sort ... "
          + "and makes Stack-is-a-List, which is false. Composition exposes only push/pop/peek.");
    }
}
