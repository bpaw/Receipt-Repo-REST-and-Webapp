package rrr.core.services.exceptions;

/**
 * Created by Brandon Paw on 7/16/2017.
 */
public class ReceiptNotFoundException extends RuntimeException {
    public ReceiptNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReceiptNotFoundException(String message) {
        super(message);
    }

    public ReceiptNotFoundException() {
    }
}