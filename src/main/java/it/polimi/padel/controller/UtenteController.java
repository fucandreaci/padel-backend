package it.polimi.padel.controller;/*
 * File: UtenteController
 * Project: Padel Backend
 * File Created: 06/09/22 - 16:57
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/utenti", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UtenteController {
}
