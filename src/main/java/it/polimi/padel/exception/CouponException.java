package it.polimi.padel.exception;/*
 * File: CouponException
 * Project: Padel Backend
 * File Created: 20/10/22 - 15:43
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import org.springframework.http.HttpStatus;

public class CouponException extends GenericException {
    public CouponException(String message, HttpStatus status) {
        super(status, message);
    }
}
