package it.polimi.padel.DTO;

import it.polimi.padel.model.Coupon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePrenotazioneWithTypeDto extends ResponsePrenotazioneDto {
    private Integer id;
    private PrenotazioneType type;
    private ResponsePartitaDto partite;
    private ResponseLezionePrivataDto lezioniPrivate;
    private Coupon coupon;
}

enum PrenotazioneType {
    LEZIONE_PRIVATA, PARTITA
}
