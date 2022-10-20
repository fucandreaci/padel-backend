package it.polimi.padel.exception;/*
 * File: TorneoException
 * Project: Padel Backend
 * File Created: 20/10/22 - 09:47
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import org.springframework.http.HttpStatus;

public class TorneoException extends GenericException{
    public TorneoException (String message, HttpStatus status) {
        super(status, message);
    }
}
