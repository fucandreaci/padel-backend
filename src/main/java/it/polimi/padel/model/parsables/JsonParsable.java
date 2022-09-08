package it.polimi.padel.model.parsables;/*
 * File: JsonParsable
 * Project: Padel Backend
 * File Created: 08/09/22 - 15:15
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import it.polimi.padel.exception.GenericException;
import org.json.JSONObject;

public interface JsonParsable {

    /**
     * Converti un JSONObject in un oggetto parsabile
     * @param json
     * @throws GenericException
     */
    void parseJson(JSONObject json) throws GenericException;
}
