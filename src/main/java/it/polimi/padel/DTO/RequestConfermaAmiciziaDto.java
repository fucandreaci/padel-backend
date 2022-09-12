package it.polimi.padel.DTO;/*
 * File: RequestConfermaAmiciziaDto
 * Project: Padel Backend
 * File Created: 12/09/22 - 19:02
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
public class RequestConfermaAmiciziaDto {
    @NotNull
    private Integer idAmico;

    @NotNull
    private Boolean conferma;
}
