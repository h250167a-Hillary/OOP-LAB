public class CurrentAccount extends Account {
    public CurrentAccount(String number, double opening) { super(number, opening); }
    @Override public double monthlyFee() { return 5.00 + balance * 0.001; }   // base + 0.1% of balance
}
