import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

/**
 * Lab 10, Exercise 5: load learners.csv, group by grade band with Collectors.groupingBy,
 * and write one report file per band. Bands: A >= 70, B 60-69, C < 60.
 */
public class GradeBands {
    record Learner(String name, int marks) {
        String band() { return marks >= 70 ? "A" : marks >= 60 ? "B" : "C"; }
    }

    public static void main(String[] args) throws IOException {
        List<Learner> learners;
        try (Stream<String> lines = Files.lines(Path.of("learners.csv"))) {
            learners = lines.skip(1)                       // skip the header
                .filter(l -> !l.isBlank())
                .map(l -> l.split(","))
                .map(p -> new Learner(p[0].trim(), Integer.parseInt(p[1].trim())))
                .toList();
        }
        Map<String, List<Learner>> byBand =
            learners.stream().collect(Collectors.groupingBy(Learner::band, TreeMap::new, Collectors.toList()));

        for (Map.Entry<String, List<Learner>> e : byBand.entrySet()) {
            String band = e.getKey();
            List<String> report = new ArrayList<>();
            report.add("Band " + band + " report (" + e.getValue().size() + " learners)");
            e.getValue().stream()
                .sorted(Comparator.comparingInt(Learner::marks).reversed())
                .forEach(l -> report.add(String.format("%-10s %3d", l.name(), l.marks())));
            Path out = Path.of("report_" + band + ".txt");
            Files.write(out, report);
            System.out.println("Wrote " + out + ":");
            report.forEach(r -> System.out.println("   " + r));
        }
    }
}
