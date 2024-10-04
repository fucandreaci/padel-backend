package it.polimi.padel.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSegnalazioneDto {
    private Integer id;
    private String chatId;
    private String messaggioId;
    private Boolean gestita;
    private List<MessaggioDto> messaggi;
}
