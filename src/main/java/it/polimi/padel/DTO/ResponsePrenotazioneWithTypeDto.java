package it.polimi.padel.DTO;

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
    private CouponDto coupon;
}

