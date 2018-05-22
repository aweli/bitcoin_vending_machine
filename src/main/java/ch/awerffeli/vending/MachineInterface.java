package ch.awerffeli.vending;

import java.util.Collection;
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
     * @param itemName the item to be purchased
     * @return true if purchase was successfull
     */
    boolean purchaseItem(String itemName);

    /**
     * Get a map of all items the machine is selling
     * @return a map with item and quantity
     */
    Collection<Item> getItemList();

    /**
     * Returns a map of all coins
     * @return
     */
    HashMap<Coin, Integer> getAvailableCoins();
}
