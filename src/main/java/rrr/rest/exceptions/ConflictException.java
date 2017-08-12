package rrr.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Brandon Paw on 7/16/2017.
 */
@ResponseStatus(value= HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {
    public ConflictException() {
    }

    public ConflictException(Throwable cause) {
        super(cause);
    }
}