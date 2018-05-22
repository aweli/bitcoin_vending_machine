package ch.awerffeli.vending;

import java.util.HashMap;

public class Balance<T> {

    final private HashMap<T, Integer> balance = new HashMap<>();

    public HashMap<T, Integer> getBalance() {
        return balance;
    }

    /**
     * If item exists set item quantity to 1, if the item exists set it to one
     * @param item
     */
    public void addItem(final T item) {
        final int quantity = balance.get(item) != null ? balance.get(item) + 1 : 1;
        balance.put(item, quantity);
    }
}
