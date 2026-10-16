import java.io.IOException;
import java.nio.file.*;
import java.util.List;

/** Lab 10, Exercise 3: count lines, words and characters (like the Unix wc command). */
public class FileStats {
    public static void main(String[] args) throws IOException {
        Path file = Path.of(args.length > 0 ? args[0] : "sample.txt");
        List<String> lines = Files.readAllLines(file);
        long words = 0, chars = 0;
        for (String line : lines) {
            String t = line.trim();
            if (!t.isEmpty()) words += t.split("\\s+").length;
            chars += line.length() + 1;     // +1 for the newline
        }
        System.out.println("File      : " + file);
        System.out.println("Lines     : " + lines.size());
        System.out.println("Words     : " + words);
        System.out.println("Characters: " + chars + " (including newlines)");
    }
}
