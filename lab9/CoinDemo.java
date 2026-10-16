import java.util.*;

/** Lab 9, Exercise 5: enum with fields and a greedy fewest-coins method. */
public class CoinDemo {
    enum Coin {
        PENNY(1), NICKEL(5), DIME(10), QUARTER(25);
        private final int cents;
        Coin(int cents) { this.cents = cents; }
        int cents() { return cents; }

        /** Greedy is optimal for this coin system. */
        static Map<Coin, Integer> fewestCoins(int amount) {
            if (amount < 0) throw new IllegalArgumentException("amount < 0");
            Map<Coin, Integer> result = new EnumMap<>(Coin.class);
            Coin[] desc = values().clone();
            Arrays.sort(desc, Comparator.comparingInt(Coin::cents).reversed());
            for (Coin c : desc) {
                int n = amount / c.cents;
                if (n > 0) { result.put(c, n); amount -= n * c.cents; }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        for (int amt : new int[]{67, 99, 30, 4, 0}) {
            Map<Coin, Integer> m = Coin.fewestCoins(amt);
            int total = m.values().stream().mapToInt(Integer::intValue).sum();
            System.out.println(amt + " cents -> " + m + " (" + total + " coins)");
        }
    }
}
