package ch.awerffeli.vending;

import ch.awerffeli.vending.exception.CoinsExchangeNotPossibleException;
import ch.awerffeli.vending.exception.NotEnoughCreditException;
import ch.awerffeli.vending.exception.SoldOutException;

import java.util.*;

import static ch.awerffeli.vending.CoinValue.*;

public class VendingMachine implements MachineInterface {

    private CoinBalance coinBalanceMachine;
    final private ItemBalance itemBalance;

    private int totalBalanceUser;

    public VendingMachine() {
        this.itemBalance = new ItemBalance();
        this.coinBalanceMachine = new CoinBalance();

        this.coinBalanceMachine.addCoin(new Coin(CENT_1), 1);
        this.coinBalanceMachine.addCoin(new Coin(CENT_2), 2);
        this.coinBalanceMachine.addCoin(new Coin(CENT_5), 5);
        this.coinBalanceMachine.addCoin(new Coin(CENT_10), 5);
        this.coinBalanceMachine.addCoin(new Coin(CENT_20), 5);
        this.coinBalanceMachine.addCoin(new Coin(CENT_50), 5);
        this.coinBalanceMachine.addCoin(new Coin(EURO_1), 5);
        this.coinBalanceMachine.addCoin(new Coin(EURO_2), 5);

        this.itemBalance.addItem("BTC", 200, 4);
        this.itemBalance.addItem("XES", 300, 5);
        this.itemBalance.addItem("ETH", 400, 6);
        this.itemBalance.addItem("BAT", 500, 7);
        this.itemBalance.addItem("NANO", 600, 8);
        this.itemBalance.addItem("XLM", 764, 9);
    }

    @Override
    public void insertCoins(List<Coin> coins) {
        totalBalanceUser += coins.stream().mapToInt(c -> c.getValue()).sum();
        coins.forEach(c -> coinBalanceMachine.addCoin(c));
    }

    @Override
    public Map<Coin, Integer> refundBalance() {
        final int tempUserBalance = this.totalBalanceUser;
        this.totalBalanceUser = 0;
        return deductCoinsFromMachine(tempUserBalance);
    }


    @Override
    public boolean purchaseItem(String itemName)
            throws SoldOutException, NotEnoughCreditException, CoinsExchangeNotPossibleException{

        final Item item = this.itemBalance.get(itemName);

        final int itemBalance = item.getQuantity();

        if(itemBalance <= 0) {
            throw new SoldOutException("Unfortunately this item is sold out.");
        }

        if(this.totalBalanceUser < item.getPrice()) {
            throw new NotEnoughCreditException("Please add more coins and try again");
        }

        deductCoinsFromMachine(item.getPrice());
        this.totalBalanceUser -= item.getPrice();

        //if possible deduct
        deductItem(item);
        return true;

    }

    /**
     * Deduct Item from Inventory (ItemBalance)
     * Check if deductCoinsFromMachine is possible before calling deductItem
     *
     * @param item the item that has been deducted
     */
    private void deductItem(Item item) {
        final int itemQuantity = item.getQuantity();

        //check again to be safe
        if(itemQuantity <= 0) {
            throw new SoldOutException("Unfortunately this item is sold out.");
        }

        item.setQuantity(itemQuantity-1);
    }

    @Override
    public Collection<Item> getItemList() {
        return this.itemBalance.getBalance().values();
    }

    @Override
    public HashMap<Coin, Integer> getAvailableCoins() {

        CoinBalance totalCoinBalance = new CoinBalance();


        final Iterator<Map.Entry<Coin, Integer>> machineCoinsIterator = this.coinBalanceMachine.getBalance().entrySet().iterator();
        while (machineCoinsIterator.hasNext()) {
            Map.Entry pair = (Map.Entry) machineCoinsIterator.next();
            Coin coin = (Coin) pair.getKey();
            int quantity = (int) pair.getValue();

            totalCoinBalance.addCoin(coin, quantity);
        }

        return totalCoinBalance.getBalance();
    }

    private int deductCoins(int removeValue, CoinValue coinValue, CoinBalance coinBalanceCopy) {
        int amount = 0;
        if(removeValue >= coinValue.getValue()) {
            amount = removeValue / coinValue.getValue();
            int balanceCoinAmount = coinBalanceCopy.getBalance().get(new Coin(coinValue));
            if(amount > balanceCoinAmount) {
                amount = balanceCoinAmount;
            }
            coinBalanceCopy.removeCoin(new Coin(coinValue), amount);

            return removeValue - (amount * coinValue.getValue());
        }

        return removeValue;
    }

    /**
     * Get a Map of removed coins
     * If no coins could be remove return null
     *
     * @param removeValue The value to be deducted
     * @throws In case machine does not have enough exchange a CoinsExchangeNotPossibleException is thrown
     * @return returns a Map of removed coins or null if removal was not possible
     */
    private Map<Coin,Integer> deductCoinsFromMachine(int removeValue) throws CoinsExchangeNotPossibleException{

        final CoinBalance coinBalanceCopy = this.coinBalanceMachine.clone();

        if(removeValue <= 0) {
            return new HashMap<>();
        }

        final CoinValue[] allCoinValues = CoinValue.getAllCoinValues();

        Arrays.sort(allCoinValues, Collections.reverseOrder());

        final Map<Coin, Integer> coinsToBeRemoved = new HashMap<>();

        int tempRemoveValue = removeValue;
        for(CoinValue coinValue : allCoinValues) {
            removeValue = deductCoins(removeValue, coinValue, coinBalanceCopy);
            if(removeValue != tempRemoveValue) {
                int difference = tempRemoveValue-removeValue;
                coinsToBeRemoved.put(new Coin(coinValue), difference / coinValue.getValue());
            }
            tempRemoveValue = removeValue;
        }

        if(removeValue > 0) {
            throw new CoinsExchangeNotPossibleException("The machine cannot give you the correct exchange, please add more coins");
        }

        //if coins could be deducted copy the new coinBalance
        this.coinBalanceMachine = coinBalanceCopy;

        return coinsToBeRemoved;
    }

}
