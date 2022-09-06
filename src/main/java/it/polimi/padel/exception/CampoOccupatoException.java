package it.polimi.padel.exception;/*
 * File: CampoNotFoundException
 * Project: Padel Backend
 * File Created: 06/09/22 - 18:39
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import org.springframework.http.HttpStatus;

public class CampoOccupatoException extends GenericException {
    public CampoOccupatoException(String message, HttpStatus status) {
        super(status, message);
    }
}