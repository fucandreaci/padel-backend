package it.polimi.padel.service;/*
 * File: PrenotazioneService
 * Project: Padel Backend
 * File Created: 06/09/22 - 16:45
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import it.polimi.padel.DTO.DtoManager;
import it.polimi.padel.DTO.RequestPrenotazioneDto;
import it.polimi.padel.exception.CampoNotFoundException;
import it.polimi.padel.exception.CampoOccupatoException;
import it.polimi.padel.exception.GenericException;
import it.polimi.padel.model.Campo;
import it.polimi.padel.model.Prenotazione;
import it.polimi.padel.repository.PrenotazioneRepository;
import it.polimi.padel.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private CampoService campoService;

    /**
     * Check se il campo specificato è libero
     * @param idCampo
     * @param da
     * @param a
     * @return
     */
    public boolean isCampoLibero (Integer idCampo, LocalDateTime da, LocalDateTime a) {
        return !prenotazioneRepository.isCampoLibero(idCampo, da, a).isEmpty();
    }

    /**
     * Check se il maestro è libero
     *
     * @param idMaestro
     * @param da
     * @param a
     * @return
     */
    public boolean isMaestroLibero (Integer idMaestro, LocalDateTime da, LocalDateTime a) {
        return !prenotazioneRepository.isMaestroLibero(idMaestro, da, a).isEmpty();
    }

    /**
     * Esegui la prenotazione di un campo
     * @param requestPrenotazioneDto
     * @return
     * @throws GenericException
     */
    public Prenotazione prenotaCampo (RequestPrenotazioneDto requestPrenotazioneDto) throws GenericException {
        Campo campo = campoService.getCampoById(requestPrenotazioneDto.getIdCampo());
        if (campo == null) {
            throw new CampoNotFoundException("Campo non trovato", HttpStatus.NOT_FOUND);
        }

        Prenotazione prenotazione = DtoManager.getPrenotazioneFromRequestPrenotazioneDto(requestPrenotazioneDto);
        prenotazione.setCampo(campo);

        if (!Utility.isValidDaADate(requestPrenotazioneDto.getDa(), requestPrenotazioneDto.getA())) {
            throw new GenericException(HttpStatus.BAD_REQUEST, "Date non valide");
        }

        if (!isCampoLibero(campo.getId(), prenotazione.getDa(), prenotazione.getA())) {
            throw new CampoOccupatoException("Campo non disponibile", HttpStatus.BAD_REQUEST);
        }
        //TODO: manage coupon

        return prenotazione;
    }

    /**
     * Salva la prenotazione sul db
     * @param prenotazione
     * @return
     */
    public Prenotazione savePrenotazione (Prenotazione prenotazione) {
        return prenotazioneRepository.save(prenotazione);
    }
}
