package it.polimi.padel.DTO;/*
 * File: RequestGestioneSegnalazione
 * Project: Padel Backend
 * File Created: 27/12/22 - 19:59
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestGestioneSegnalazioneDto {
    private Integer id;
    private Boolean blocco;
}
