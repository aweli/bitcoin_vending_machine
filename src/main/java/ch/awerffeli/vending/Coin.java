package ch.awerffeli.vending;

import ch.awerffeli.vending.exception.VendingMachineException;

public class Coin {

    final CoinValue coinValue;

    public Coin(CoinValue coinValue) {
        if(coinValue == null) {
            throw new VendingMachineException("CoinValue has to be set");
        }
        this.coinValue = coinValue;
    }

    public int getValue() {
        return coinValue.getValue();
    }

    @Override
    public boolean equals(Object obj) {
        return !(obj instanceof Coin) ? false : this.coinValue.getValue() == ((Coin)obj).coinValue.getValue();
    }

    @Override
    public int hashCode() {
        return coinValue.getValue();
    }
}
