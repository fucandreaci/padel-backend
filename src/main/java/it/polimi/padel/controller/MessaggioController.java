package it.polimi.padel.controller;/*
 * File: MessaggioController
 * Project: Padel Backend
 * File Created: 14/10/22 - 11:02
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import it.polimi.padel.DTO.InviaMessaggioDto;
import it.polimi.padel.exception.UserException;
import it.polimi.padel.model.Utente;
import it.polimi.padel.service.FirebaseService;
import it.polimi.padel.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/messaggi", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MessaggioController {
    @Autowired
    private FirebaseService firebaseService;

    @Autowired
    private UtenteService utenteService;

    @PostMapping("/invia")
    public ResponseEntity<?> inviaRichiestaAmicizia (@Valid @RequestBody InviaMessaggioDto messaggioDto) {
        Utente richiedente = utenteService.findFromJWT();
        Utente destinatario;

        try {
            destinatario = utenteService.findById(messaggioDto.getIdDestinatario());

            if (destinatario == null) {
                throw new UserException("Utente non trovato", HttpStatus.NOT_FOUND);
            }
            firebaseService.inviaMessaggio(richiedente, destinatario, messaggioDto.getMessaggio());
        } catch (UserException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatus());
        }
        return ResponseEntity.ok(null);
    }
}
