/**
 * Lab 6, Exercise 5. If a class inherits two default methods with the same signature the
 * compiler cannot choose, so it reports an error unless the class OVERRIDES hello().
 * Inside the override, InterfaceName.super.hello() selects which default to call.
 * Rule: (1) a class method always wins over an interface default; (2) a more specific
 * interface wins over a less specific one; (3) otherwise the class must resolve it explicitly.
 */
public class DiamondDemo implements HelloA, HelloB {
    @Override
    public void hello() {
        HelloA.super.hello();
        HelloB.super.hello();
        System.out.println("Hello from DiamondDemo (conflict resolved)");
    }

    public static void main(String[] args) { new DiamondDemo().hello(); }
}
