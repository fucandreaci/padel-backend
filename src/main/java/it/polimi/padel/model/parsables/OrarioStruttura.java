package it.polimi.padel.model.parsables;/*
 * File: OrariStruttura
 * Project: Padel Backend
 * File Created: 08/09/22 - 15:21
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import it.polimi.padel.exception.GenericException;
import it.polimi.padel.exception.ParseException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrarioStruttura implements JsonParsable{
    private String giorno;
    private LocalTime dalle;
    private LocalTime alle;


    @Override
    public void parseJson(JSONObject json) throws GenericException {
        try {
            this.setGiorno(json.getString("giorno"));
            this.setDalle(LocalTime.parse(json.getString("dalle")));
            this.setDalle(LocalTime.parse(json.getString("alle")));
        } catch (JSONException e) {
            throw new ParseException("Errore nel parsing dell'orario della struttura", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
