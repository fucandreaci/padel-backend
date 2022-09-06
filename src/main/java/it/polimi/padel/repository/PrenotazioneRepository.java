package it.polimi.padel.repository;/*
 * File: PrenotazioneRepository
 * Project: Padel Backend
 * File Created: 06/09/22 - 16:43
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import it.polimi.padel.model.Prenotazione;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PrenotazioneRepository extends CrudRepository<Prenotazione, Integer> {
    @Query("SELECT p FROM Prenotazione p WHERE p.campo.id = ?1 AND p.da <= ?3 AND p.a >= ?2")
    List<Prenotazione> isCampoLibero (Integer idCampo, LocalDateTime da, LocalDateTime a);
}
