package it.polimi.padel.exception;/*
 * File: ParseException
 * Project: Padel Backend
 * File Created: 08/09/22 - 15:23
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import org.springframework.http.HttpStatus;

public class ParseException extends GenericException {
    public ParseException(String message, HttpStatus httpStatus) {
        super(httpStatus, message);
    }
}