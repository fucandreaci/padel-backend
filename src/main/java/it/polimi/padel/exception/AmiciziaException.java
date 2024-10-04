package it.polimi.padel.exception;

import org.springframework.http.HttpStatus;

public class AmiciziaException extends GenericException {
    public AmiciziaException(String message, HttpStatus status) {
        super(status, message);
    }
}