package it.polimi.padel.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCreaTorneoDto {
    @NotNull
    private Integer maxPartecipanti;

    @NotNull
    @NotBlank
    private String descrizione;
}
