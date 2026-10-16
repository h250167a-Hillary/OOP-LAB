import java.util.*;

/** Lab 8, Exercises 1 & 2. */
public class ListStats {
    public static void main(String[] args) {
        // Exercise 1: sum, max, reverse
        List<Integer> nums = new ArrayList<>(List.of(14, 3, 27, 9, 41, 8, 22, 35, 6, 19));
        int sum = 0;
        for (int n : nums) sum += n;
        System.out.println("List   : " + nums);
        System.out.println("Sum    : " + sum);
        System.out.println("Max    : " + Collections.max(nums));
        List<Integer> reversed = new ArrayList<>(nums);
        Collections.reverse(reversed);
        System.out.println("Reverse: " + reversed);

        // Exercise 2: remove duplicates with a Set (LinkedHashSet keeps the first-seen order)
        List<String> names = List.of("Larry", "Hillary", "Larry", "Tinevimbo", "Hillary", "Tatenda", "Tinevimbo");
        Set<String> unique = new LinkedHashSet<>(names);
        System.out.println("Names  : " + names);
        System.out.println("Unique : " + unique);
    }
}
