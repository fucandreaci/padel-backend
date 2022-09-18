package it.polimi.padel.repository;/*
 * File: LezionePrivataRepository
 * Project: Padel Backend
 * File Created: 06/09/22 - 16:41
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import it.polimi.padel.model.LezionePrivata;
import it.polimi.padel.model.Utente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LezionePrivataRepository extends CrudRepository<LezionePrivata, Integer> {
    List<LezionePrivata> findAllByUtente(Utente utente);
}
