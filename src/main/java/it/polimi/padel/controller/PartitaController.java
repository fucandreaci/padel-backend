package it.polimi.padel.controller;/*
 * File: PartitaController
 * Project: Padel Backend
 * File Created: 06/09/22 - 16:56
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import it.polimi.padel.DTO.RequestPartitaDto;
import it.polimi.padel.DTO.ResponsePartitaDto;
import it.polimi.padel.exception.GenericException;
import it.polimi.padel.model.Prenotazione;
import it.polimi.padel.model.Utente;
import it.polimi.padel.service.PartitaService;
import it.polimi.padel.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/partite", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PartitaController {
    @Autowired
    private PartitaService partitaService;

    @Autowired
    private UtenteService utenteService;

    /**
     * Prenota una partita specificando il campo e l'orario
     * @param requestPartitaDto
     * @return
     */
    @PostMapping("")
    public ResponseEntity<?> prenotaPartita (@Valid @RequestBody RequestPartitaDto requestPartitaDto) {
        Utente utente = utenteService.findFromJWT();

        try {
            ResponsePartitaDto responsePartitaDto = partitaService.prenotaPartita(requestPartitaDto, utente);
            return ResponseEntity.ok(responsePartitaDto);
        } catch (GenericException e) {
            return new ResponseEntity<>(e.getError(), e.getStatus());
        }
    }
}
