package it.polimi.padel.controller;

import it.polimi.padel.service.InformazioniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/informazioni", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class InformazioniController {
    @Autowired
    private InformazioniService informazioniService;

    @GetMapping("")
    public ResponseEntity<?> getInformazioni () {
        return ResponseEntity.ok(informazioniService.getInformazioni());
    }
}
