package ch.awerffeli.vending.exception;

public class NotEnoughCreditException extends VendingMachineException {
    public NotEnoughCreditException(String message) {
        super(message);
    }
}
