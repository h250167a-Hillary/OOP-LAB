/**
 * Exercise 4 explanation: if Dog declares no constructor, the compiler adds
 * Dog() { super(); }. super() looks for Animal(), which does not exist because Animal
 * defines Animal(String). Compilation fails ("constructor Animal in class Animal cannot be
 * applied to given types"). See DogBroken.java.txt. FIX: call super(name) explicitly.
 */
public class Dog extends Animal {
    public Dog(String name) {
        super(name);   // the fix
    }

    public static void main(String[] args) {
        Dog d = new Dog("Rex");
        System.out.println("Dog created: " + d.name);
    }
}
