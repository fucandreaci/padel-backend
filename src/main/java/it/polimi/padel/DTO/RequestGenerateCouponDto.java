package it.polimi.padel.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestGenerateCouponDto {
    @NotNull
    @NotBlank
    private String tipo;

    @NotNull
    private Float valore;
}
