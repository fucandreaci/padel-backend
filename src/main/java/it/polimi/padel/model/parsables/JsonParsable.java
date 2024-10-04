package it.polimi.padel.model.parsables;

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
