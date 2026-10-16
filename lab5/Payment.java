public abstract class Payment {
    protected final double amount;
    protected Payment(double amount) { this.amount = amount; }
    public double getAmount() { return amount; }
    /** Polymorphic design (version 2): each subclass knows how to process itself. */
    public abstract void process();
}
