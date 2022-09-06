package it.polimi.padel.model;/*
 * File: Torneo
 * Project: Padel Backend
 * File Created: 06/09/22 - 13:22
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
public class Torneo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer maxPartecipanti;

    @ManyToMany
    @JoinTable(
            name = "torneo_utenti",
            joinColumns = @JoinColumn(name = "id_torneo", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_utente", referencedColumnName = "id")
    )
    private List<Utente> utenti = new ArrayList<>();
}
