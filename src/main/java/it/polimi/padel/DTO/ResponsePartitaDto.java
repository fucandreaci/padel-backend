package it.polimi.padel.DTO;/*
 * File: ResponsePartitaDto
 * Project: Padel Backend
 * File Created: 06/09/22 - 19:09
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePartitaDto extends ResponsePrenotazioneDto{
    private String nomeAvversario;
    private String cognomeAvversario;
    private Integer idAvversario;

}
