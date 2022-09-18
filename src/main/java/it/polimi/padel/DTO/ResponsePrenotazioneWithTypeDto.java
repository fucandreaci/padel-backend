package it.polimi.padel.DTO;/*
 * File: ResponsePrenotazioneWithTypeDto
 * Project: Padel Backend
 * File Created: 17/09/22 - 21:49
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePrenotazioneWithTypeDto extends ResponsePrenotazioneDto {
    private Integer id;
    private PrenotazioneType type;
    private ResponsePartitaDto partite;
    private ResponseLezionePrivataDto lezioniPrivate;
}

enum PrenotazioneType {
    LEZIONE_PRIVATA, PARTITA
}
