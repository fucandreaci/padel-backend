package it.polimi.padel.repository;/*
 * File: MaestroRepository
 * Project: Padel Backend
 * File Created: 06/09/22 - 16:42
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import it.polimi.padel.model.Maestro;
import org.springframework.data.repository.CrudRepository;

public interface MaestroRepository extends CrudRepository<Maestro, Integer> {

}
