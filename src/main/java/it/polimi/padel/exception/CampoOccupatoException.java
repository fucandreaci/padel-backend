package it.polimi.padel.exception;

import org.springframework.http.HttpStatus;

public class CampoOccupatoException extends GenericException {
    public CampoOccupatoException(String message, HttpStatus status) {
        super(status, message);
    }
}