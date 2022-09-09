package it.polimi.padel.DTO;/*
 * File: RequestAmiciziaDto
 * Project: Padel Backend
 * File Created: 09/09/22 - 10:31
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestAmiciziaDto {
    @NotNull
    private Integer idUtente;
}
