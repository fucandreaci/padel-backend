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

    /**
     * Ottieni tutte le informazioni
     * @return
     */
    @GetMapping("")
    public ResponseEntity<?> getInformazioni () {
        return ResponseEntity.ok(informazioniService.getInformazioni());
    }

    /**
     * Ottieni le news
     * @return
     */
    @GetMapping("/getNews")
    public ResponseEntity<?> getNews () {
        try {
            return ResponseEntity.ok(informazioniService.getNews());
        } catch (GenericException e) {
            return new ResponseEntity<>(e.getError(), e.getStatus());
        }
    }

    /**
     * Ottieni informazioni varie sulla struttura
     * @return
     */
    @GetMapping("/getInfoVarie")
    public ResponseEntity<?> getInfoVarie () {
        try {
            return ResponseEntity.ok(informazioniService.getInfoVarie());
        } catch (GenericException e) {
            return new ResponseEntity<>(e.getError(), e.getStatus());
        }
    }

    /**
     * Ottieni la lista di contatti
     * @return
     */
    @GetMapping("/getContatti")
    public ResponseEntity<?> getContatti () {
        try {
            return ResponseEntity.ok(informazioniService.getContatti());
        } catch (GenericException e) {
            return new ResponseEntity<>(e.getError(), e.getStatus());
        }
    }

    /**
     * Ottieni gli orari di apertura della struttura
     * @return
     */
    @GetMapping("/getOrari")
    public ResponseEntity<?> getOrari () {
        try {
            return ResponseEntity.ok(informazioniService.getOrariApertura());
        } catch (GenericException e) {
            return new ResponseEntity<>(e.getError(), e.getStatus());
        }
    }

    /**
     * Ottieni la lista delle regole
     * @return
     */
    @GetMapping("/getRegole")
    public ResponseEntity<?> getRegole () {
        try {
            return ResponseEntity.ok(informazioniService.getRegole());
        } catch (GenericException e) {
            return new ResponseEntity<>(e.getError(), e.getStatus());
        }
    }
}
