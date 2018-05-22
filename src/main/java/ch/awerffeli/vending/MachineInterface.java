package ch.awerffeli.vending;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Interface for a vending machine.
 * @author Alex Werffeli
 */
public interface MachineInterface {

    /**
     * Inserts coins to the machine
     * 
     * @param coins the coin inserted
     */
    void insertCoins(List<Coin> coins);

    /**
     * Returns the inserted an unspent coins to the user
     * 
     * @return a map of coins to be refunded
     */
    Map<Coin, Integer> refundBalance();

    /**
     * Purchase an item with the given itemName if enough coins have been inserted.
     * 
     * @param item the item to be purchased
     * @return returns the purchased item
     */
    Item purchaseItem(String itemName);

    /**
     * Get a list of all items the machine is selling
     * @return
     */
    List<Item> getItemList();

    /**
     * Returns a map of all coins
     * @return
     */
    HashMap<Coin, Integer> getAvailableCoins();
}
