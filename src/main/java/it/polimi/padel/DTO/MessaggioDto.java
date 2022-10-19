package it.polimi.padel.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessaggioDto {
    private UtenteDto sender;
    private UtenteDto receiver;
    private String message;
    private String time;
}
