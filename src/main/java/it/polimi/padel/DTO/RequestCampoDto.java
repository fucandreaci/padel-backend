package it.polimi.padel.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCampoDto {
    @NotBlank
    @NotNull
    private String nome;

    @NotBlank
    @NotNull
    private String urlImmagine;
}
