package it.polimi.padel.utils;/*
 * File: Utility
 * Project: Padel Backend
 * File Created: 06/09/22 - 16:49
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import it.polimi.padel.model.parsables.OrarioStruttura;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.regex.Pattern;

public class Utility {
    public static boolean isValidEmail (String email) {
        final String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        return Pattern.compile(regexPattern).matcher(email).matches();
    }

    public static boolean isValidDaADate (LocalDateTime da, LocalDateTime a) {
        return da.isBefore(a);
    }

    public static boolean isStrutturaAperta (OrarioStruttura orarioStruttura, LocalDateTime da, LocalDateTime a) {
        LocalTime daTime = da.toLocalTime();
        LocalTime aTime = a.toLocalTime();

        return orarioStruttura.getDalle().isBefore(daTime) && orarioStruttura.getAlle().isAfter(aTime);
    }


}
