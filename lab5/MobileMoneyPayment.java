public class MobileMoneyPayment extends Payment {
    private final String phone;
    public MobileMoneyPayment(double amount, String phone) { super(amount); this.phone = phone; }
    public String getPhone() { return phone; }
    @Override public void process() { System.out.printf("Requesting mobile money from %s for $%.2f%n", phone, amount); }
}
