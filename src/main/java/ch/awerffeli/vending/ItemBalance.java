package ch.awerffeli.vending;

import java.util.HashMap;

public class ItemBalance{

    final private HashMap<String, Item> balance = new HashMap<>();

    public Item get(final String name) {
        return balance.get(name);
    }

    public HashMap<String, Item> getBalance() {
        return balance;
    }

    /**
     * If item exists set item quantity to 1, if the item exists set it to one
     * @param name the name of the item
     */
    public void addItem(final String name, int price, int quantity) {
        final Item item = balance.get(name);
        if(item != null) {
            return;
        }

        balance.put(name, new Item(name, price, quantity));
    }

}
