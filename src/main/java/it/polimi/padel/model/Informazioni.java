package it.polimi.padel.model;/*
 * File: Informazioni
 * Project: Padel Backend
 * File Created: 06/09/22 - 13:16
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Informazioni {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String chiave;

    @Column(length = 5000)
    private String valore;
}
