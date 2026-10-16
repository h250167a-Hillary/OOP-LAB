import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Lab 3, Exercise 3: BankAccount with a transaction history. */
public class BankAccount {
    private final String accountNumber;
    private String ownerName;
    private double balance;
    private final List<String> history = new ArrayList<>();

    public BankAccount(String accountNumber, String ownerName, double openingBalance) {
        if (openingBalance < 0) throw new IllegalArgumentException("Opening balance cannot be negative");
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = openingBalance;
        history.add(String.format("OPEN    %.2f -> balance %.2f", openingBalance, balance));
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public double getBalance() { return balance; }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit must be positive");
        balance += amount;
        history.add(String.format("DEPOSIT %.2f -> balance %.2f", amount, balance));
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Withdrawal must be positive");
        if (amount > balance) {
            history.add(String.format("FAILED  withdraw %.2f (insufficient funds)", amount));
            return false;
        }
        balance -= amount;
        history.add(String.format("WITHDRAW %.2f -> balance %.2f", amount, balance));
        return true;
    }

    /** Returns an UNMODIFIABLE COPY, so callers can neither change nor see later changes to the internal list. */
    public List<String> getHistory() {
        return Collections.unmodifiableList(new ArrayList<>(history));
    }

    public static void main(String[] args) {
        BankAccount acc = new BankAccount("ACC-001", "Munya", 500);
        acc.deposit(250);
        acc.withdraw(100);
        acc.withdraw(5000);
        List<String> h = acc.getHistory();
        h.forEach(System.out::println);
        try {
            h.add("HACK: +1000000");   // attempt to tamper
        } catch (UnsupportedOperationException e) {
            System.out.println("Tamper attempt blocked: history is unmodifiable");
        }
        System.out.println("Entries recorded: " + acc.getHistory().size());
    }
}
