package it.polimi.padel.DTO;/*
 * File: ResponsePrenotazioneDto
 * Project: Padel Backend
 * File Created: 06/09/22 - 19:08
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePrenotazioneDto {
    private CampoDto campo;
    private LocalDateTime da;
    private LocalDateTime a;
}
