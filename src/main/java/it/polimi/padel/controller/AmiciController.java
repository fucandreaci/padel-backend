package it.polimi.padel.controller;/*
 * File: AmiciController
 * Project: Padel Backend
 * File Created: 06/09/22 - 16:53
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import it.polimi.padel.DTO.RequestAmiciziaDto;
import it.polimi.padel.exception.AmiciziaException;
import it.polimi.padel.exception.UserException;
import it.polimi.padel.model.Utente;
import it.polimi.padel.service.AmiciService;
import it.polimi.padel.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/amici", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AmiciController {
    @Autowired
    private AmiciService amiciService;

    @Autowired
    private UtenteService utenteService;

    /**
     * Invia una richiesta di amicizia
     * @param requestAmiciziaDto
     * @return
     */
    @PostMapping("/invia")
    public ResponseEntity<?> inviaRichiestaAmicizia (@Valid @RequestBody RequestAmiciziaDto requestAmiciziaDto) {
        //TODO: Da provare la richiesta
        Utente richiedente = utenteService.findFromJWT();
        try {
            amiciService.inviaRichiestaAmicizia(requestAmiciziaDto, richiedente);
            return ResponseEntity.ok(null);
        } catch (UserException | AmiciziaException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatus());
        }
    }
}
