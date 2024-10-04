package it.polimi.padel.DTO;
import it.polimi.padel.model.Ruolo;
import it.polimi.padel.model.Utente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTPayload {
    private Integer id;
    private Ruolo role;

    public static JWTPayload parseUser (Utente user) {
        return new JWTPayload(user.getId(), user.getRuolo());
    }
}
