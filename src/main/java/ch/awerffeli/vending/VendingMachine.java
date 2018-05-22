package ch.awerffeli.vending;

import ch.awerffeli.vending.exception.CoinsExchangeNotPossibleException;
import ch.awerffeli.vending.exception.NotEnoughCreditException;
import ch.awerffeli.vending.exception.SoldOutException;

import java.util.*;

import static ch.awerffeli.vending.CoinValue.*;

public class VendingMachine implements MachineInterface {


    final private CoinBalance coinBalanceMachine;
    private CoinBalance coinBalanceUser;
    final private ItemBalance itemBalance;

    public VendingMachine() {
        this.coinBalanceMachine = new CoinBalance();
        this.itemBalance = new ItemBalance();
        this.coinBalanceUser = new CoinBalance();

        this.coinBalanceMachine.addCoin(new Coin(CENT_1), 1);
        this.coinBalanceMachine.addCoin(new Coin(CENT_2), 2);
        this.coinBalanceMachine.addCoin(new Coin(CENT_5), 5);
        this.coinBalanceMachine.addCoin(new Coin(CENT_10), 5);
        this.coinBalanceMachine.addCoin(new Coin(CENT_20), 5);
        this.coinBalanceMachine.addCoin(new Coin(CENT_50), 5);
        this.coinBalanceMachine.addCoin(new Coin(EURO_1), 5);
        this.coinBalanceMachine.addCoin(new Coin(EURO_2), 5);

        this.itemBalance.addItem(new Item("BTC", 200));
        this.itemBalance.addItem(new Item("XES", 350));
        this.itemBalance.addItem(new Item("ETH", 200));
        this.itemBalance.addItem(new Item("XLM", 220));
        this.itemBalance.addItem(new Item("LTC", 220));
    }

    @Override
    public void insertCoins(List<Coin> coins) {
        coins.forEach(c -> coinBalanceUser.addCoin(c));
    }

    @Override
    public HashMap<Coin, Integer> refundBalance() {
        final HashMap<Coin, Integer> balance = coinBalanceUser.getBalance();
        this.coinBalanceUser = new CoinBalance();

        return balance;
    }

    @Override
    public Item purchaseItem(String itemName) {

        Item item = new Item(itemName);

        final int totalBalanceValue = coinBalanceMachine.getTotalBalanceValue();
        final HashMap<Item, Integer> itemBalance = this.itemBalance.getBalance();

        if(itemBalance.get(item) <= 0) {
            throw new SoldOutException("Unfortunately this item is sold out.");
        }

        if(totalBalanceValue < item.getPrice()) {
            throw new NotEnoughCreditException("Please add more coins and try again");
        }

        //check if can make exchange
        if(possibleToDeductCoins() != true) {
            throw new CoinsExchangeNotPossibleException("The machine cannot give you the correct exchange, please add more coins");
        }

        //if possible deduct
        deductCoins(item);
        deductItem(item);
        return item;

    }

    private void deductItem(Item item) {
        //todo: implement
    }

    private void deductCoins(Item item) {
        //todo: implement
    }


    private boolean possibleToDeductCoins() {
        //todo: implement
        return false;
    }

    @Override
    public List<Item> getItemList() {
        return new ArrayList(this.itemBalance.getBalance().keySet());
    }

    @Override
    public HashMap<Coin, Integer> getAvailableCoins() {

        CoinBalance totalCoinBalance = new CoinBalance();


        final Iterator<Map.Entry<Coin, Integer>> machineCoinsIterator = this.coinBalanceMachine.getBalance().entrySet().iterator();
        while(machineCoinsIterator.hasNext()) {
            Map.Entry pair = (Map.Entry)machineCoinsIterator.next();
            Coin coin = (Coin) pair.getKey();
            int quantity = (int) pair.getValue();

            totalCoinBalance.addCoin(coin, quantity);
        }

        final Iterator<Map.Entry<Coin, Integer>> userCoinsIterator = this.coinBalanceUser.getBalance().entrySet().iterator();
        while(userCoinsIterator.hasNext()) {
            Map.Entry pair = (Map.Entry)userCoinsIterator.next();
            Coin coin = (Coin) pair.getKey();
            int quantity = (int) pair.getValue();

            totalCoinBalance.addCoin(coin, quantity);
        }

        return totalCoinBalance.getBalance();

    }

}
