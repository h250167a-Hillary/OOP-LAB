public class SavingsAccount extends Account {
    public SavingsAccount(String number, double opening) { super(number, opening); }
    @Override public double monthlyFee() { return 2.00; }           // flat fee
}
