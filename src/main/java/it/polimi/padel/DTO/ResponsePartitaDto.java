package it.polimi.padel.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePartitaDto extends ResponsePrenotazioneDto{
    private String nomeAvversario;
    private String cognomeAvversario;
    private Integer idAvversario;

}
