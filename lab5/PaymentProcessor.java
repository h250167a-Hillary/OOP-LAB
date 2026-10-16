/**
 * Lab 5, Exercise 5: two designs.
 * Version 1 : pay() uses instanceof pattern matching. Every new payment type forces an edit
 *             here (open for modification, closed for extension); a forgotten branch fails silently.
 * Version 2 : payPolymorphic() just calls p.process(). A new type (e.g. BankTransfer) only needs
 *             a new class; PaymentProcessor never changes (Open/Closed Principle).
 */
public class PaymentProcessor {
    // Design 1: type switching with instanceof patterns
    public void pay(Payment p) {
        if (p instanceof CardPayment c) {
            System.out.printf("[v1] Charging card ****%s for $%.2f%n", c.last4(), c.getAmount());
        } else if (p instanceof MobileMoneyPayment m) {
            System.out.printf("[v1] Requesting mobile money from %s for $%.2f%n", m.getPhone(), m.getAmount());
        } else {
            throw new IllegalArgumentException("Unsupported payment type: " + p.getClass().getSimpleName());
        }
    }

    // Design 2: polymorphism
    public void payPolymorphic(Payment p) {
        System.out.print("[v2] ");
        p.process();
    }

    public static void main(String[] args) {
        PaymentProcessor proc = new PaymentProcessor();
        Payment[] payments = {
            new CardPayment(120.50, "4111111111111234"),
            new MobileMoneyPayment(35, "+263772418365")
        };
        for (Payment p : payments) proc.pay(p);
        for (Payment p : payments) proc.payPolymorphic(p);
    }
}
