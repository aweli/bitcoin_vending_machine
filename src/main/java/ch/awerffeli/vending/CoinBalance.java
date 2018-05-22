package ch.awerffeli.vending;

import ch.awerffeli.vending.exception.CoinsExchangeNotPossibleException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static ch.awerffeli.vending.CoinValue.*;

public class CoinBalance {

    protected HashMap<Coin, Integer> balance;

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

    public int getTotalBalanceValue() {
        int totalAmount = 0;

        final Iterator<Map.Entry<Coin, Integer>> iterator = this.balance.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry pair = iterator.next();
            Coin coin = (Coin) pair.getKey();
            int quantity = (int) pair.getValue();

            totalAmount += coin.getValue()*quantity;
        }

        return totalAmount;
    }

    public void addCoin(Coin coin) {
        addCoin(coin, 1);
    }

    public void addCoin(Coin coin, int quantity) {
        balance.put(coin, balance.get(coin) + quantity);
    }

    public void removeCoin(Coin coin, int quantity) {
        final Integer currentQuantity = this.balance.get(coin);
        if (currentQuantity < quantity) {
            throw new CoinsExchangeNotPossibleException("cannot remove coin, reason: not enough coins");
        }

        balance.put(coin, currentQuantity - quantity);

    }

    public void removeCoin(Coin coin) {
        removeCoin(coin, 1);
    }

    public CoinBalance clone() {
        CoinBalance coinBalanceClone = new CoinBalance();
        coinBalanceClone.balance = this.balance;

        return coinBalanceClone;
    }

}
