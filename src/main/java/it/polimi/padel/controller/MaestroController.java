package it.polimi.padel.controller;

import it.polimi.padel.service.MaestroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/maestri", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MaestroController {
    @Autowired
    private MaestroService maestroService;

    /**
     * Ottieni i maestri
     * @return
     */
    @GetMapping("")
    public ResponseEntity<?> getMaestri () {
        return ResponseEntity.ok(maestroService.getMaestri());
    }
}
