package it.polimi.padel.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericException extends Exception{
    private HttpStatus status;
    private String message;

    public ErrorResponse getError() {
        return new ErrorResponse(message, status);
    }
}
