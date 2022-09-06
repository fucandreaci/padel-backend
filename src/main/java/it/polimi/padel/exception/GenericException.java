package it.polimi.padel.exception;/*
 * File: GenericException
 * Project: Padel Backend
 * File Created: 06/09/22 - 17:48
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class GenericException extends Exception{
    private HttpStatus status;
    private String message;

    public ErrorResponse getError() {
        return new ErrorResponse(message, status);
    }
}
