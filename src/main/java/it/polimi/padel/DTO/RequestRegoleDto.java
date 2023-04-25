package it.polimi.padel.DTO;/*
 * File: RequestGenerateCouponDto
 * Project: Padel Backend
 * File Created: 28/12/22 - 18:52
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

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
