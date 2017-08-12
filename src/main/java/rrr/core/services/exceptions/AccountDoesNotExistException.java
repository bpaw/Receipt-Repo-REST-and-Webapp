package rrr.core.services.exceptions;

import rrr.core.models.entities.Account;

/**
 * Created by Brandon Paw on 7/16/2017.
 */
public class AccountDoesNotExistException extends RuntimeException {

    public AccountDoesNotExistException(Throwable cause) {
        super(cause);
    }

    public AccountDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountDoesNotExistException(String message) {
        super(message);
    }

    public AccountDoesNotExistException() {}
}
