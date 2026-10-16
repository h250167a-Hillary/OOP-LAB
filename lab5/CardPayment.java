public class CardPayment extends Payment {
    private final String cardNumber;
    public CardPayment(double amount, String cardNumber) { super(amount); this.cardNumber = cardNumber; }
    public String last4() { return cardNumber.substring(cardNumber.length() - 4); }
    @Override public void process() { System.out.printf("Charging card ****%s for $%.2f%n", last4(), amount); }
}
