package it.polimi.padel.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InviaMessaggioDto {
    @NotNull
    private Integer idDestinatario;

    @NotNull
    @NotBlank
    private String messaggio;
}
