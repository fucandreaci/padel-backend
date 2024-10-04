package it.polimi.padel.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseLezionePrivataDto extends ResponsePrenotazioneDto{
    private String nomeMaestro;
    private String cognomeMaestro;
    private Integer idMaestro;

}
