package it.polimi.padel.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/maestri", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MaestroController {
}
