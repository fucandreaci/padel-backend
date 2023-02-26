package it.polimi.padel.DTO;/*
 * File: CouponDto
 * Project: Padel Backend
 * File Created: 26/02/23 - 17:40
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2023-2023 Andrea Fucci
 */

import it.polimi.padel.model.Coupon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponDto {
    private Integer id;
    private String codice;
    private Float valore;
    private Coupon.TipoCoupon tipo;
}
