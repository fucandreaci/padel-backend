package it.polimi.padel.DTO;/*
 * File: MessaggioDto
 * Project: Padel Backend
 * File Created: 14/10/22 - 10:58
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessaggioDto {
    private UtenteDto sender;
    private UtenteDto receiver;
    private String message;
    private String time;
}
