package it.polimi.padel.DTO;/*
 * File: InviaMessaggioDto
 * Project: Padel Backend
 * File Created: 14/10/22 - 11:04
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

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
