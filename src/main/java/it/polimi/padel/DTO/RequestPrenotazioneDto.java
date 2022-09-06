package it.polimi.padel.DTO;/*
 * File: RequestPrenotazioneDto
 * Project: Padel Backend
 * File Created: 06/09/22 - 18:36
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestPrenotazioneDto {
    @NotNull
    private Integer idCampo;

    @NotNull
    private LocalDateTime da; //FIXME: Vengono decrementate 2 ore

    @NotNull
    private LocalDateTime a; //FIXME: Vengono decrementate 2 ore
}
