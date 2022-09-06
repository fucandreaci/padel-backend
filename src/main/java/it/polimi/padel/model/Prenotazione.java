package it.polimi.padel.model;/*
 * File: Prenotazione
 * Project: Padel Backend
 * File Created: 06/09/22 - 13:25
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime da;
    private LocalDateTime a;

    @OneToOne
    @JoinColumn(name = "id_coupon")
    private Coupon coupon;

    @OneToOne
    @JoinColumn(name = "id_lezionePrivata")
    private LezionePrivata lezionePrivata;

    @OneToOne
    @JoinColumn(name = "id_partita")
    private Partita partita;

    @ManyToOne
    @JoinColumn(name = "id_campo")
    private Campo campo;
}
