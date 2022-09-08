package it.polimi.padel.controller;/*
 * File: LezionePrivataController
 * Project: Padel Backend
 * File Created: 06/09/22 - 16:55
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import it.polimi.padel.DTO.RequestLezionePrivataDto;
import it.polimi.padel.exception.GenericException;
import it.polimi.padel.model.Utente;
import it.polimi.padel.service.LezionePrivataService;
import it.polimi.padel.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/lezioniprivate", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LezionePrivataController {
    @Autowired
    private LezionePrivataService lezionePrivataService;

    @Autowired
    private UtenteService utenteService;

    @PostMapping("")
    public ResponseEntity<?> prenotaLezionePrivata(@Valid @RequestBody RequestLezionePrivataDto lezionePrivataDto) {
        Utente richiedente = utenteService.findFromJWT();

        try {
            return ResponseEntity.ok(lezionePrivataService.prenotaLezionePrivata(lezionePrivataDto, richiedente));
        } catch (GenericException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatus());
        }
    }
}
