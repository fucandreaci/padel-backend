package it.polimi.padel.service;/*
 * File: PrenotazioneService
 * Project: Padel Backend
 * File Created: 06/09/22 - 16:45
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import it.polimi.padel.repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    public boolean isCampoLibero (Integer idCampo, LocalDateTime da, LocalDateTime a) {
        return !prenotazioneRepository.isCampoLibero(idCampo, da, a).isEmpty();
    }
}
