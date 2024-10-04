package it.polimi.padel.DTO;
import it.polimi.padel.model.parsables.OrarioStruttura;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrariAperturaDto {
    @NotNull
    private List<OrarioStruttura> orari;

}
