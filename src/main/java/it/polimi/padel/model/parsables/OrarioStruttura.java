package it.polimi.padel.model.parsables;

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
            this.setAlle(LocalTime.parse(json.getString("alle")));
        } catch (JSONException e) {
            throw new ParseException("Errore nel parsing dell'orario della struttura", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
