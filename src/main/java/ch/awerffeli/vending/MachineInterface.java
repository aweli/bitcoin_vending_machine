package ch.awerffeli.vending;

import java.util.List;

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
    void insertCash(List<Coin> coins);

    /**
     * Returns the inserted an unspent coins to the user
     * 
     * @return a list of coins to be refunded
     */
    List<Coin> refundBalance();

    /**
     * Purchase an item with the given itemName if enough coins have been inserted.
     * 
     * @param itemName the name of the item to be purchased
     * @return returns the purchased item
     */
    Item purchaseItem(int itemName);

    /**
     * Get a list of all items the machine is selling
     * @return
     */
    List<Item> getItemList();

    //todo: how to handle amount of coins

    /**
     * Returns a list of all items 
     * @return
     */
    List<Coin> getAvailableCoins();


}
