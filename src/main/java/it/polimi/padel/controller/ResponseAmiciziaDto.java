package it.polimi.padel.controller;/*
 * File: ResponseAmiciziaDto
 * Project: Padel Backend
 * File Created: 12/09/22 - 19:08
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

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
