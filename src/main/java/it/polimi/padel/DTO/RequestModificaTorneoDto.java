package it.polimi.padel.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestModificaTorneoDto extends RequestCreaTorneoDto{
    @NotNull
    private Boolean prenotazioneAperta;
}
