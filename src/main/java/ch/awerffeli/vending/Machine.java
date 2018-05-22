package ch.awerffeli.vending;

import java.util.List;

public class Machine implements MachineInterface{


    final private CoinBalance coinBalance;
    final private ItemBalance itemBalance;

    public Machine() {
        this.coinBalance = new CoinBalance();
        this.itemBalance = new ItemBalance();

        this.itemBalance.addItem(new Item("BTC", 200));
        this.itemBalance.addItem(new Item("XES", 350));
        this.itemBalance.addItem(new Item("ETH", 200));
        this.itemBalance.addItem(new Item("XLM", 220));
        this.itemBalance.addItem(new Item("LTC", 220));
    }

    @Override
    public void insertCash(List<Coin> coins) {
        coins.forEach(c -> coinBalance.addCoin(c));
    }

    @Override
    public List<Coin> refundBalance() {
        return null;
    }

    @Override
    public Item purchaseItem(int itemName) {
        return null;
    }

    @Override
    public List<Item> getItemList() {
        return null;
    }

    @Override
    public List<Coin> getAvailableCoins() {
        return null;
    }

}
