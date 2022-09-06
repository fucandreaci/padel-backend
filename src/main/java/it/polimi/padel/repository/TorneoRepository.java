package it.polimi.padel.repository;/*
 * File: TorneoRepository
 * Project: Padel Backend
 * File Created: 06/09/22 - 16:43
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import it.polimi.padel.model.Torneo;
import org.springframework.data.repository.CrudRepository;

public interface TorneoRepository extends CrudRepository<Torneo, Integer> {

}
