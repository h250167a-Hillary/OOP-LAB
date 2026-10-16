/** Lab 9, Exercise 1: enum + switch. */
public class LevelDemo {
    enum Level { LOW, MEDIUM, HIGH }

    static String advice(Level level) {
        return switch (level) {
            case LOW    -> "Relax, but keep an eye on it.";
            case MEDIUM -> "Plan ahead and monitor closely.";
            case HIGH   -> "Act now!";
        };
    }

    public static void main(String[] args) {
        for (Level l : Level.values()) System.out.println(l + " (ordinal " + l.ordinal() + "): " + advice(l));
        System.out.println("valueOf(\"HIGH\") == Level.HIGH ? " + (Level.valueOf("HIGH") == Level.HIGH));
    }
}
