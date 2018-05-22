package ch.awerffeli.vending;

//todo: is this needed
// 1ct, 2ct, 5ct, 10ct, 20ct, 50ct, 1€, 2€.
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


}
