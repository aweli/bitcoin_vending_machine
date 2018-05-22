package ch.awerffeli.vending;

import ch.awerffeli.vending.exception.CoinsExchangeNotPossibleException;

import java.util.HashMap;

import static ch.awerffeli.vending.Coin.*;

public class CoinBalance {

    private HashMap<Coin, Integer> balance;

    protected CoinBalance() {
        this.balance = new HashMap<>();

        balance.put(CENT_1, 0);
        balance.put(CENT_2, 0);
        balance.put(CENT_5, 0);
        balance.put(CENT_10, 0);
        balance.put(CENT_20, 0);
        balance.put(CENT_50, 0);
        balance.put(EURO_1, 0);
        balance.put(EURO_2, 0);
    }

    protected HashMap<Coin, Integer> getBalance() {
        return balance;
    }

    public int getTotalBalanceValue() {
        return this.balance.entrySet().stream().map(e -> e.getKey().getValue() * e.getValue()).mapToInt(Number::intValue).sum();
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
