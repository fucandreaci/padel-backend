package it.polimi.padel.service;/*
 * File: CampoService
 * Project: Padel Backend
 * File Created: 06/09/22 - 16:44
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import it.polimi.padel.model.Campo;
import it.polimi.padel.repository.CampoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CampoService {
    @Autowired
    private CampoRepository campoRepository;

    /**
     * Ritorna il campo con l'id specificato
     * @param id
     * @return
     */
    public Campo getCampoById (Integer id) {
        return campoRepository.findById(id).orElse(null);
    }
}
