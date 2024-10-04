package it.polimi.padel.exception;

import org.springframework.http.HttpStatus;

public class CampoNotFoundException extends GenericException {
    public CampoNotFoundException(String message, HttpStatus status) {
        super(status, message);
    }
}