package it.polimi.padel.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePrenotazioneDto {
    private CampoDto campo;
    private LocalDateTime da;
    private LocalDateTime a;
}
