package it.polimi.padel.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestGestioneSegnalazioneDto {
    private Integer id;
    private Boolean blocco;
}
