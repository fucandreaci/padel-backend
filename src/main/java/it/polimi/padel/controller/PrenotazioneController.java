package it.polimi.padel.controller;/*
 * File: PrenotazioneController
 * Project: Padel Backend
 * File Created: 06/09/22 - 16:57
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import it.polimi.padel.service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/prenotazioni", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PrenotazioneController {
    @Autowired
    private PrenotazioneService prenotazioneService;


}
