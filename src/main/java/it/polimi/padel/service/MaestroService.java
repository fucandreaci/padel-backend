package it.polimi.padel.service;/*
 * File: MaestroService
 * Project: Padel Backend
 * File Created: 06/09/22 - 16:44
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import it.polimi.padel.model.Maestro;
import it.polimi.padel.repository.MaestroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MaestroService {
    @Autowired
    private MaestroRepository maestroRepository;

    public Maestro getMaestroById (int id) {
        return maestroRepository.findById(id).orElse(null);
    }
}
