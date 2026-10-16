/**
 * Lab 2, Exercise 4: two static blocks and two instance blocks.
 * PREDICTION (static parts run once at class load, in textual order;
 * instance parts run per object, in textual order, BEFORE the constructor body):
 *   static block 1
 *   static block 2
 *   --- main starts ---
 *   instance block 1
 *   instance block 2
 *   constructor
 *   instance block 1
 *   instance block 2
 *   constructor
 */
public class BlockOrder {
    static { System.out.println("static block 1"); }
    { System.out.println("instance block 1"); }
    static { System.out.println("static block 2"); }
    { System.out.println("instance block 2"); }

    BlockOrder() { System.out.println("constructor"); }

    public static void main(String[] args) {
        System.out.println("--- main starts ---");
        new BlockOrder();
        new BlockOrder();
    }
}
