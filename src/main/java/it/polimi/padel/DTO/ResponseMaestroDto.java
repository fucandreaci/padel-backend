package it.polimi.padel.DTO;/*
 * File: ResponseMaestroDto
 * Project: Padel Backend
 * File Created: 25/09/22 - 01:09
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseMaestroDto {
    private Integer id;
    private String nome;
    private String cognome;
}
