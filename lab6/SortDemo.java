import java.util.*;

public class SortDemo {
    public static void main(String[] args) {
        List<Learner> list = new ArrayList<>(List.of(
            new Learner("Larry", 78), new Learner("Hillary", 64),
            new Learner("Tatenda", 91), new Learner("Munya", 55)));

        Collections.sort(list);   // uses compareTo -> by name
        System.out.println("By name (Comparable)      : " + list);

        Comparator<Learner> byMarks = Comparator.comparingInt(Learner::getMarks).reversed();
        list.sort(byMarks);       // highest marks first
        System.out.println("By marks desc (Comparator): " + list);
    }
}
