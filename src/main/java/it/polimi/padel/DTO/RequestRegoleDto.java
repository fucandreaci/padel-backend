package it.polimi.padel.DTO;

import it.polimi.padel.model.parsables.Regola;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestRegoleDto {
    @NotNull
    private List<Regola> regole;
}
