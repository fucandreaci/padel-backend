package it.polimi.padel.model.parsables;

import it.polimi.padel.exception.GenericException;
import it.polimi.padel.exception.ParseException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoVarie implements JsonParsable{
    private String nome;
    private String descrizione;

    @Override
    public void parseJson(JSONObject json) throws GenericException {
        try {
            this.setDescrizione(json.getString("descrizione"));
            this.setNome(json.getString("nome"));
        } catch (JSONException e) {
            throw new ParseException("Errore nel parsing delle informazioni", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
