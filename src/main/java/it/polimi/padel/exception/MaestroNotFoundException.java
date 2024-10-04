package it.polimi.padel.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class MaestroNotFoundException extends GenericException{
    public MaestroNotFoundException(String message, HttpStatus status) {
        super(status, message);
    }
}
