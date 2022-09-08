package it.polimi.padel.DTO;/*
 * File: RequestPartitaDto
 * Project: Padel Backend
 * File Created: 06/09/22 - 18:57
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
public class RequestLezionePrivataDto extends RequestPrenotazioneDto{
    @NotNull
    private Integer idMaestro;
}
