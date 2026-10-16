public class FlyDemo {
    public static void main(String[] args) {
        // Bird and Aeroplane share NO superclass but can be used through one interface type
        Flyable[] things = { new Bird(), new Aeroplane() };
        for (Flyable f : things) f.fly();
    }
}
