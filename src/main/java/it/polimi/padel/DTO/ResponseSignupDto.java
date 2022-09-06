package it.polimi.padel.DTO;/*
 * File: ResponseSignupDto
 * Project: Padel Backend
 * File Created: 06/09/22 - 16:48
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseSignupDto {
    private String nome;
    private String cognome;
    private String email;
}
