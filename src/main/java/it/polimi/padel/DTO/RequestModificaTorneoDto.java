package it.polimi.padel.DTO;/*
 * File: RequestModificaTorneoDto
 * Project: Padel Backend
 * File Created: 30/10/22 - 13:37
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
public class RequestModificaTorneoDto extends RequestCreaTorneoDto{
    @NotNull
    private Boolean prenotazioneAperta;
}
