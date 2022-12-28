package it.polimi.padel.DTO;/*
 * File: RequestGenerateCouponDto
 * Project: Padel Backend
 * File Created: 28/12/22 - 18:52
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

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
