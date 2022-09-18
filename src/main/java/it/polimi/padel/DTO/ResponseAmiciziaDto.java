package it.polimi.padel.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAmiciziaDto {
    private Integer idAmico;
    private String nomeAmico;
    private String cognomeAmico;
    private Boolean accettata;
}
