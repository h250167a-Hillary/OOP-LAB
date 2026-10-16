import java.util.ArrayList;
import java.util.List;

/** Tiny CSV helper: quotes fields containing commas, quotes or newlines. */
final class Csv {
    private Csv() {}

    static String join(String... fields) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fields.length; i++) {
            if (i > 0) sb.append(',');
            sb.append(escape(fields[i]));
        }
        return sb.toString();
    }

    static String escape(String f) {
        if (f.contains(",") || f.contains("\"") || f.contains("\n")) {
            return "\"" + f.replace("\"", "\"\"") + "\"";
        }
        return f;
    }

    static List<String> parse(String line) {
        List<String> out = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        boolean quoted = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (quoted) {
                if (c == '"') {
                    if (i + 1 < line.length() && line.charAt(i + 1) == '"') { cur.append('"'); i++; }
                    else quoted = false;
                } else cur.append(c);
            } else if (c == '"') quoted = true;
            else if (c == ',') { out.add(cur.toString()); cur.setLength(0); }
            else cur.append(c);
        }
        out.add(cur.toString());
        return out;
    }
}
