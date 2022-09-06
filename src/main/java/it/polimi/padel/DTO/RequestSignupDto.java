package it.polimi.padel.DTO;/*
 * File: RequestSignupDto
 * Project: Padel Backend
 * File Created: 06/09/22 - 16:47
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestSignupDto {
    private String nome;
    private String cognome;
    private String email;
    private String password;
}
