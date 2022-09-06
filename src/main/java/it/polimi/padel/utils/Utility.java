package it.polimi.padel.utils;/*
 * File: Utility
 * Project: Padel Backend
 * File Created: 06/09/22 - 16:49
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import java.util.regex.Pattern;

public class Utility {
    public static boolean isValidEmail (String email) {
        final String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        return Pattern.compile(regexPattern).matcher(email).matches();
    }
}
