package it.polimi.padel.service;/*
 * File: PartitaService
 * Project: Padel Backend
 * File Created: 06/09/22 - 16:45
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import it.polimi.padel.DTO.DtoManager;
import it.polimi.padel.DTO.RequestPartitaDto;
import it.polimi.padel.DTO.ResponsePartitaDto;
import it.polimi.padel.exception.GenericException;
import it.polimi.padel.exception.UserException;
import it.polimi.padel.model.Partita;
import it.polimi.padel.model.Prenotazione;
import it.polimi.padel.model.Utente;
import it.polimi.padel.repository.PartitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional(rollbackOn = GenericException.class)
public class PartitaService {
    @Autowired
    private PartitaRepository partitaRepository;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private PrenotazioneService prenotazioneService;

    /**
     * Esegui la prenotazione di una nuova partita specificando il campo e l'orario
     * @param requestPartitaDto
     * @param richiedente
     * @return
     * @throws GenericException
     */
    public ResponsePartitaDto prenotaPartita (RequestPartitaDto requestPartitaDto, Utente richiedente) throws GenericException {
        Partita partita = new Partita();
        partita.setUtente1(richiedente);
        partita = partitaRepository.save(partita);

        Prenotazione prenotazione = prenotazioneService.prenotaCampo(requestPartitaDto);
        prenotazione.setPartita(partita);
        prenotazione.setLezionePrivata(null);
        prenotazione = prenotazioneService.savePrenotazione(prenotazione);

        return DtoManager.getResponsePartitaDtoFromPartita(prenotazione);
    }
}
