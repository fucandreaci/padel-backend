package it.polimi.padel.repository;/*
 * File: CouponRepository
 * Project: Padel Backend
 * File Created: 06/09/22 - 16:41
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import it.polimi.padel.model.Coupon;
import org.springframework.data.repository.CrudRepository;

public interface CouponRepository extends CrudRepository<Coupon, Integer> {

}
