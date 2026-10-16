public class Dvd extends LibraryItem {
    private final int durationMinutes;

    public Dvd(String id, String title, int durationMinutes) {
        super(id, title);
        if (durationMinutes <= 0) throw new IllegalArgumentException("Duration must be positive");
        this.durationMinutes = durationMinutes;
    }

    public int getDurationMinutes() { return durationMinutes; }

    @Override public int loanPeriodDays() { return 7; }     // DVDs are lent for a shorter time
    @Override public String type() { return "DVD"; }
    @Override public String extraField() { return String.valueOf(durationMinutes); }

    @Override public String toString() { return super.toString() + "  (" + durationMinutes + " min)"; }
}
