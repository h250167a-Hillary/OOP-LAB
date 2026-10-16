public class AccountDemo {
    public static void main(String[] args) {
        Account[] accounts = { new SavingsAccount("SAV-1", 1000), new CurrentAccount("CUR-1", 2000) };
        for (Account a : accounts) {
            a.deposit(500);
            System.out.println("Before: " + a);
            a.applyMonthEnd();
            System.out.println("After : " + a);
        }
    }
}
