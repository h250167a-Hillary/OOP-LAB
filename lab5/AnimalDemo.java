/** Lab 5, Exercise 2: Animal[] holding subclasses; each call dispatches to the real object's method. */
public class AnimalDemo {
    public static void main(String[] args) {
        Animal[] zoo = { new Cat(), new Cow(), new Duck(), new Animal() };
        for (Animal a : zoo) {
            System.out.println(a.getClass().getSimpleName() + " says " + a.sound());
        }
    }
}
