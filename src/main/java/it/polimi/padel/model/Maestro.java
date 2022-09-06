package it.polimi.padel.model;/*
 * File: Maestro
 * Project: Padel Backend
 * File Created: 06/09/22 - 13:21
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Maestro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String cognome;

    @OneToMany(mappedBy = "maestro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LezionePrivata> lezioniPrivate = new ArrayList<>();
}
