package it.polimi.padel.controller;

import it.polimi.padel.exception.GenericException;
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

    @GetMapping("/getNews")
    public ResponseEntity<?> getNews () {
        try {
            return ResponseEntity.ok(informazioniService.getNews());
        } catch (GenericException e) {
            return new ResponseEntity<>(e.getError(), e.getStatus());
        }
    }

    @GetMapping("/getInfoVarie")
    public ResponseEntity<?> getInfoVarie () {
        try {
            return ResponseEntity.ok(informazioniService.getInfoVarie());
        } catch (GenericException e) {
            return new ResponseEntity<>(e.getError(), e.getStatus());
        }
    }
}
