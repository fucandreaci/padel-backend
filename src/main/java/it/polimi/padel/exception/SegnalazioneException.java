package it.polimi.padel.exception;

import org.springframework.http.HttpStatus;

public class SegnalazioneException extends GenericException {
    public SegnalazioneException (String message, HttpStatus status) {
        super(status, message);
    }
}
