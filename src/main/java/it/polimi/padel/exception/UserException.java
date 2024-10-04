package it.polimi.padel.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class UserException extends GenericException{
    public UserException(String message, HttpStatus status) {
        super(status, message);
    }
}
