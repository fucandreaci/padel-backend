package it.polimi.padel.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestPrenotazioneDto {
    @NotNull
    private Integer idCampo;

    @NotNull
    private LocalDateTime da; //FIXME: Vengono decrementate 2 ore

    @NotNull
    private LocalDateTime a; //FIXME: Vengono decrementate 2 ore

    @NotNull
    private String codiceCoupon;
}
