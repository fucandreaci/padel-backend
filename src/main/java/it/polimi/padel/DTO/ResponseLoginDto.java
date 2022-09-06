package it.polimi.padel.DTO;/*
 * File: ResponseloginDto
 * Project: Padel Backend
 * File Created: 06/09/22 - 16:36
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseLoginDto {
    private String token;
    private String ruolo;
    private Integer id;
}
