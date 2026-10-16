/** Lab 6, Exercise 4: Comparable (natural order by name) + Comparator (by marks). */
public class Learner implements Comparable<Learner> {
    private final String name;
    private final int marks;

    public Learner(String name, int marks) { this.name = name; this.marks = marks; }
    public String getName() { return name; }
    public int getMarks() { return marks; }

    @Override public int compareTo(Learner other) { return this.name.compareToIgnoreCase(other.name); }
    @Override public String toString() { return name + "(" + marks + ")"; }
}
