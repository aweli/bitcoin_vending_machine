package ch.awerffeli.vending;

public enum CoinValue {

    CENT_1(1),
    CENT_2(2),
    CENT_5(5),
    CENT_10(10),
    CENT_20(20),
    CENT_50(50),
    EURO_1(100),
    EURO_2(200);

    private final int value;

    CoinValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static CoinValue[] getAllCoinValues() {
        CoinValue[] coinValues = {CENT_1, CENT_2, CENT_5, CENT_10, CENT_20, CENT_50, EURO_1, EURO_2};
        return coinValues;
    }

}
