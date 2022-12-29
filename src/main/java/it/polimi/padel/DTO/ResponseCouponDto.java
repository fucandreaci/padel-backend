package it.polimi.padel.DTO;/*
 * File: ResponseCouponDto
 * Project: Padel Backend
 * File Created: 29/12/22 - 22:09
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import it.polimi.padel.model.Coupon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseCouponDto {
    private Integer id;
    private String codice;
    private Float valore;
    private Coupon.TipoCoupon tipo;
    private Boolean utilizzato;
}
