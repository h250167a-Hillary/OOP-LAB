import java.io.IOException;
import java.nio.file.*;
import java.util.*;

/** Lab 10, Exercise 1: save five typed lines to a file, then read them back. */
public class SaveLines {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        List<String> lines = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            System.out.print("Line " + i + ": ");
            lines.add(in.hasNextLine() ? in.nextLine() : "");
        }
        Path file = Path.of("notes.txt");
        Files.write(file, lines);                         // writes and closes the file
        System.out.println("\nSaved " + lines.size() + " lines to " + file);
        System.out.println("--- reading back ---");
        for (String s : Files.readAllLines(file)) System.out.println(s);
    }
}
