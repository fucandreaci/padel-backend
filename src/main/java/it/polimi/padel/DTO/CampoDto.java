package it.polimi.padel.DTO;/*
 * File: CampoDto
 * Project: Padel Backend
 * File Created: 06/09/22 - 20:22
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampoDto {
    private Integer id;
    private String nome;
}
