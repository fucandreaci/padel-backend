package it.polimi.padel.model;/*
 * File: Coupon
 * Project: Padel Backend
 * File Created: 06/09/22 - 13:18
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String codice;
    private Float valore;
    private TipoCoupon tipo;

    public enum TipoCoupon {
        PERCENTUALE, EURO
    }
}
