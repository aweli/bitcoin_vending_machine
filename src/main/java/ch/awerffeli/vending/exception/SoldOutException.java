package ch.awerffeli.vending.exception;

public class SoldOutException extends VendingMachineException {
    public SoldOutException(String message) {
        super(message);
    }
}
