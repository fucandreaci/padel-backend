package it.polimi.padel.repository;/*
 * File: AmiciRepository
 * Project: Padel Backend
 * File Created: 06/09/22 - 16:40
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import it.polimi.padel.model.Amici;
import it.polimi.padel.model.Utente;
import org.springframework.data.repository.CrudRepository;

public interface AmiciRepository extends CrudRepository<Amici, Integer> {
    Amici findByUtente1AndUtente2 (Utente utente1, Utente utente2);
}
