package it.polimi.padel.DTO;/*
 * File: RequestInformazioniDto
 * Project: Padel Backend
 * File Created: 06/03/23 - 18:51
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2023-2023 Andrea Fucci
 */

import it.polimi.padel.model.parsables.OrarioStruttura;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestNewsDto {
    @NotNull
    private String news;

}
