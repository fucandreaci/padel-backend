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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestSignupDto {
    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    @NotBlank
    private String cognome;

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String password;
}
