package ch.awerffeli.vending;

import java.util.HashMap;

import static ch.awerffeli.vending.CoinValue.*;

public class ItemBalance extends Balance<Item>{

    public ItemBalance() {
        super();
        balance.put(new Item("BTC", 300), 0);
        balance.put(new Item("XES", 200), 0);
        balance.put(new Item("ETH", 150), 0);
        balance.put(new Item("BAT", 220), 0);
        balance.put(new Item("NANO", 120), 0);
        balance.put(new Item("XLM", 125), 0);
    }
}
