package it.polimi.padel.exception;

import org.springframework.http.HttpStatus;

public class CouponException extends GenericException {
    public CouponException(String message, HttpStatus status) {
        super(status, message);
    }
}
