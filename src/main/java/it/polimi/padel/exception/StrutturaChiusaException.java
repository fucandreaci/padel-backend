package it.polimi.padel.exception;

import org.springframework.http.HttpStatus;

public class StrutturaChiusaException extends GenericException {
    public StrutturaChiusaException(String message, HttpStatus status) {
        super(status, message);
    }
}