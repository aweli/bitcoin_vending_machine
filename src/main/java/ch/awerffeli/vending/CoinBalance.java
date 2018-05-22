package ch.awerffeli.vending;

import java.util.HashMap;
import static ch.awerffeli.vending.CoinValue.*;

//todo: extend Coinbalance
public class CoinBalance {

    HashMap<Coin, Integer> balance;

    public CoinBalance() {
        this.balance = new HashMap<>();

        balance.put(new Coin(CENT_1), 0);
        balance.put(new Coin(CENT_2), 0);
        balance.put(new Coin(CENT_5), 0);
        balance.put(new Coin(CENT_10), 0);
        balance.put(new Coin(CENT_20), 0);
        balance.put(new Coin(CENT_50), 0);
        balance.put(new Coin(EURO_1), 0);
        balance.put(new Coin(EURO_2), 0);
    }

    public HashMap<Coin, Integer> getBalance() {
        return balance;
    }

    public void addCoin(Coin coin) {
        balance.put(coin, balance.get(coin) + 1);
    }
}
