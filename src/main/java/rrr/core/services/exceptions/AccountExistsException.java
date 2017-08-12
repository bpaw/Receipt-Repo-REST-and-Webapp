package rrr.core.services.exceptions;

/**
 * Created by Brandon Paw on 7/16/2017.
 */
public class AccountExistsException extends RuntimeException {
    public AccountExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountExistsException(String message) {
        super(message);
    }

    public AccountExistsException() {
        super();
    }
}