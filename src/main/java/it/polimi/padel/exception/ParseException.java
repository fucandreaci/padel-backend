package it.polimi.padel.exception;

import org.springframework.http.HttpStatus;

public class ParseException extends GenericException {
    public ParseException(String message, HttpStatus httpStatus) {
        super(httpStatus, message);
    }
}