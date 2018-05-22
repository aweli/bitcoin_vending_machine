package ch.awerffeli.vending.exception;

//todo: is runtime correct?
public class VendingMachineException extends RuntimeException{

    public VendingMachineException(String message) {
        super(message);
    }
}
