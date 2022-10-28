package it.polimi.padel.exception;


import org.springframework.http.HttpStatus;

public class TorneoException extends GenericException{
    public TorneoException (String message, HttpStatus status) {
        super(status, message);
    }
}
