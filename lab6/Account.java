/** Lab 6, Exercise 3: abstract Account with concrete deposit() and abstract monthlyFee(). */
public abstract class Account {
    private final String number;
    protected double balance;

    protected Account(String number, double opening) { this.number = number; this.balance = opening; }

    public void deposit(double amount) {            // concrete: same for every account type
        if (amount <= 0) throw new IllegalArgumentException("Deposit must be positive");
        balance += amount;
    }

    public abstract double monthlyFee();             // each account type decides its own fee

    public void applyMonthEnd() { balance -= monthlyFee(); }
    public String getNumber() { return number; }
    public double getBalance() { return balance; }

    @Override public String toString() {
        return String.format("%s %s balance=%.2f fee=%.2f", getClass().getSimpleName(), number, balance, monthlyFee());
    }
}
