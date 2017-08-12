package rrr.core.services.exceptions;

/**
 * Created by Brandon Paw on 7/16/2017.
 */
public class ReceiptExistsException extends RuntimeException {
    public ReceiptExistsException() {
    }

    public ReceiptExistsException(String message) {
        super(message);
    }

    public ReceiptExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReceiptExistsException(Throwable cause) {
        super(cause);
    }
}