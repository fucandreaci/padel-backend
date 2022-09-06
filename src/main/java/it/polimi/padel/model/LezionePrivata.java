package it.polimi.padel.model;/*
 * File: LezionePrivata
 * Project: Padel Backend
 * File Created: 06/09/22 - 13:23
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LezionePrivata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_maestro")
    private Maestro maestro;

    @ManyToOne
    @JoinColumn(name = "id_utente")
    private Utente utente;
}
