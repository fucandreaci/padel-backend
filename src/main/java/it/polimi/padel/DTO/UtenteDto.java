package it.polimi.padel.DTO;/*
 * File: UtenteDto
 * Project: Padel Backend
 * File Created: 14/10/22 - 10:58
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UtenteDto {
    private Integer id;
    private String nome;
    private String cognome;
}
