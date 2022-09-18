package it.polimi.padel.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestConfermaAmiciziaDto {
    @NotNull
    private Integer idAmico;

    @NotNull
    private Boolean conferma;
}
