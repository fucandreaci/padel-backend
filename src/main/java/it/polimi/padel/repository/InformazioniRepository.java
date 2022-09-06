package it.polimi.padel.repository;/*
 * File: InformazioniRepository
 * Project: Padel Backend
 * File Created: 06/09/22 - 16:41
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import it.polimi.padel.model.Informazioni;
import org.springframework.data.repository.CrudRepository;

public interface InformazioniRepository extends CrudRepository<Informazioni, Integer> {

}
