/**
 * Lab 7, Exercise 4: withdraw now throws InsufficientFundsException.
 * Trade-offs vs returning boolean:
 *  + The failure cannot be silently ignored (checked exception forces handling) and carries
 *    information (shortfall, message). Normal code path stays clean.
 *  + A boolean return conflates 'failed' with other reasons and is easy to forget to test.
 *  - Callers need try/catch boilerplate; exceptions are slower than a return value;
 *  - An insufficient balance is a fairly EXPECTED event, and some argue expected outcomes
 *    are better modelled by return values/Optional; exceptions suit exceptional conditions.
 */
public class BankAccount {
    private final String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double opening) {
        if (opening < 0) throw new IllegalArgumentException("Opening balance cannot be negative");
        this.accountNumber = accountNumber;
        this.balance = opening;
    }

    public double getBalance() { return balance; }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit must be positive");
        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) throw new IllegalArgumentException("Withdrawal must be positive");
        if (amount > balance) throw new InsufficientFundsException(balance, amount);
        balance -= amount;
    }

    public static void main(String[] args) {
        BankAccount acc = new BankAccount("ACC-001", 500);
        try {
            acc.withdraw(200);
            System.out.println("Withdrew 200, balance " + acc.getBalance());
            acc.withdraw(1000);
            System.out.println("not reached");
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage() + " | short by " + e.getShortfall());
        }
        System.out.println("Balance unchanged by failed withdrawal: " + acc.getBalance());
    }
}
