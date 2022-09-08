package it.polimi.padel.service;/*
 * File: LezionePrivataService
 * Project: Padel Backend
 * File Created: 06/09/22 - 16:44
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import it.polimi.padel.DTO.DtoManager;
import it.polimi.padel.DTO.RequestLezionePrivataDto;
import it.polimi.padel.DTO.ResponseLezionePrivataDto;
import it.polimi.padel.exception.GenericException;
import it.polimi.padel.exception.MaestroNotFoundException;
import it.polimi.padel.model.LezionePrivata;
import it.polimi.padel.model.Maestro;
import it.polimi.padel.model.Prenotazione;
import it.polimi.padel.model.Utente;
import it.polimi.padel.repository.LezionePrivataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class LezionePrivataService {
    @Autowired
    private LezionePrivataRepository lezionePrivataRepository;

    @Autowired
    private PrenotazioneService prenotazioneService;

    @Autowired
    private MaestroService maestroService;

    public ResponseLezionePrivataDto prenotaLezionePrivata (RequestLezionePrivataDto lezionePrivataDto, Utente richiedente) throws GenericException {
        Maestro maestro = maestroService.getMaestroById(lezionePrivataDto.getIdMaestro());
        if (maestro == null) {
            throw new MaestroNotFoundException("Il maestro non esiste", HttpStatus.NOT_FOUND);
        }

        LezionePrivata lezionePrivata = new LezionePrivata();
        lezionePrivata.setUtente(richiedente);
        lezionePrivata.setMaestro(maestro);
        lezionePrivata = lezionePrivataRepository.save(lezionePrivata);

        Prenotazione prenotazione = prenotazioneService.prenotaCampo(lezionePrivataDto);
        prenotazione.setLezionePrivata(lezionePrivata);
        prenotazione = prenotazioneService.savePrenotazione(prenotazione);

        return DtoManager.getResponseLezionePrivataDtoFromLezionePrivata(prenotazione);
        //TODO: test
    }
}
