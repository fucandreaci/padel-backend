package it.polimi.padel.DTO;/*
 * File: RequestCreaTorneoDto
 * Project: Padel Backend
 * File Created: 20/10/22 - 10:32
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
public class RequestCreaTorneoDto {
    @NotNull
    private Integer maxPartecipanti;

    @NotNull
    @NotBlank
    private String descrizione;
}
