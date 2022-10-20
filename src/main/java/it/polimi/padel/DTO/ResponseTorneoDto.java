package it.polimi.padel.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTorneoDto {
    private Integer id;
    private Integer maxPartecipanti;
    private Boolean prenotazioneAperta;
    private Integer numPartecipanti;
    private Boolean utentePrenotato;
    private String descrizione;
}
