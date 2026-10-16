import java.util.*;
import java.util.stream.Collectors;

/** Lab 8, Exercise 4: word frequency, sorted by count descending (ties alphabetical). */
public class WordFrequency {
    public static void main(String[] args) {
        String paragraph = """
            Object oriented programming organises software around objects. An object bundles data
            and behaviour. Classes describe objects, and objects interact through methods. The four
            pillars of object oriented programming are encapsulation, inheritance, polymorphism and
            abstraction. The pillars make programs easier to change.
            """;
        Map<String, Integer> freq = new HashMap<>();
        for (String w : paragraph.toLowerCase().split("[^a-z]+")) {
            if (!w.isEmpty()) freq.merge(w, 1, Integer::sum);
        }
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(freq.entrySet());
        entries.sort(Map.Entry.<String, Integer>comparingByValue().reversed()
                     .thenComparing(Map.Entry.comparingByKey()));
        for (Map.Entry<String, Integer> e : entries.subList(0, 10)) {
            System.out.printf("%-14s %d%n", e.getKey(), e.getValue());
        }
        System.out.println("(" + freq.size() + " distinct words; top 10 shown)");
    }
}
