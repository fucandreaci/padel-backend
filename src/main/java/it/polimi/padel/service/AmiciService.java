package it.polimi.padel.service;/*
 * File: AmiciService
 * Project: Padel Backend
 * File Created: 06/09/22 - 16:43
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import it.polimi.padel.DTO.DtoManager;
import it.polimi.padel.DTO.RequestAmiciziaDto;
import it.polimi.padel.DTO.RequestConfermaAmiciziaDto;
import it.polimi.padel.DTO.ResponseAmiciziaDto;
import it.polimi.padel.exception.AmiciziaException;
import it.polimi.padel.exception.UserException;
import it.polimi.padel.model.Amici;
import it.polimi.padel.model.Utente;
import it.polimi.padel.repository.AmiciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AmiciService {
    @Autowired
    private AmiciRepository amiciRepository;

    @Autowired
    private UtenteService utenteService;

    /**
     * Controllo che l'utente non abbia già una richiesta di amicizia inviata all'utente specificato
     * @param utente1
     * @param utente2
     * @return
     */
    private boolean existAmicizia (Utente utente1, Utente utente2) {
        return amiciRepository.findByUtente1AndUtente2(utente1, utente2) != null;
    }

    /**
     * Invia una richiesta di amicizia
     * @param requestAmiciziaDto
     * @param richiedente
     * @throws UserException
     * @throws AmiciziaException
     */
    public void inviaRichiestaAmicizia (RequestAmiciziaDto requestAmiciziaDto, Utente richiedente) throws UserException, AmiciziaException {
        Utente amicoDaAgg = utenteService.findById(requestAmiciziaDto.getIdUtente());
        if (amicoDaAgg == null) {
            throw new UserException("L'utente non esiste", HttpStatus.NOT_FOUND);
        }

        if (existAmicizia(richiedente, amicoDaAgg)) {
            throw new AmiciziaException("L'amicizia esiste già", HttpStatus.BAD_REQUEST);
        }

        if (amicoDaAgg.getId() == richiedente.getId()) {
            throw new AmiciziaException("Non puoi aggiungerti da solo", HttpStatus.BAD_REQUEST);
        }

        Amici amici = new Amici();
        amici.setUtente1(richiedente);
        amici.setUtente2(amicoDaAgg);
        amici.setUtente(richiedente);

        amiciRepository.save(amici);
    }

    /**
     * Conferma una richiesta di amicizia specificando se accettare o rifiutare
     * @param confermaAmiciziaDto
     * @param richiedente
     * @throws AmiciziaException
     */
    public void accettaRichiestaAmicizia (RequestConfermaAmiciziaDto confermaAmiciziaDto, Utente richiedente) throws AmiciziaException {
        Utente amico = utenteService.findById(confermaAmiciziaDto.getIdAmico());
        if (amico == null) {
            throw new AmiciziaException("L'utente non esiste", HttpStatus.NOT_FOUND);
        }

        if (!existAmicizia(richiedente, amico)) {
            throw new AmiciziaException("L'amicizia non esiste", HttpStatus.NOT_FOUND);
        }

        Amici amicizia = amiciRepository.findByUtente1AndUtente2(richiedente, amico);
        if (amicizia.getAccettata() != null) {
            throw new AmiciziaException("L'amicizia è già stata accettata/rifiutata", HttpStatus.BAD_REQUEST);
        }

        amicizia.setAccettata(confermaAmiciziaDto.getConferma());
        amiciRepository.save(amicizia);
    }

    /**
     * Ritorna la lista delle richieste di amicizia ricevute
     * @param richiedente
     * @return
     */
    public List<ResponseAmiciziaDto> getAmicizieInSospeso (Utente richiedente) {
        List<Amici> amicizie = amiciRepository.findAllByUtente1AndAccettataIsNull(richiedente);
        List<ResponseAmiciziaDto> dtos = amicizie.stream().map(amicizia -> DtoManager.getResponseAmiciziaDtoFromAmici(amicizia)).collect(Collectors.toList());
        return dtos;
    }

    /**
     * Ritorna la lista degli amici
     * @param richiedente
     * @return
     */
    public List<ResponseAmiciziaDto> getAmicizieAccettate (Utente richiedente) {
        List<Amici> amicizie = amiciRepository.findAllByUtente1AndAccettataIsTrue(richiedente);
        List<ResponseAmiciziaDto> dtos = amicizie.stream().map(amicizia -> DtoManager.getResponseAmiciziaDtoFromAmici(amicizia)).collect(Collectors.toList());
        return dtos;
    }
}
