package it.polimi.padel.repository;/*
 * File: PrenotazioneRepository
 * Project: Padel Backend
 * File Created: 06/09/22 - 16:43
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import it.polimi.padel.model.Prenotazione;
import org.springframework.data.repository.CrudRepository;

public interface PrenotazioneRepository extends CrudRepository<Prenotazione, Integer> {

}
