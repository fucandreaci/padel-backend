package it.polimi.padel.controller;

import it.polimi.padel.model.Utente;
import it.polimi.padel.service.PrenotazioneService;
import it.polimi.padel.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/prenotazioni", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PrenotazioneController {
    @Autowired
    private PrenotazioneService prenotazioneService;

    @Autowired
    private UtenteService utenteService;

    /**
     * Ottieni le prenotazioni dell'utente
     * @return
     */
    @GetMapping("")
    public ResponseEntity<?> getPrenotazioni() {
        Utente richiedente = utenteService.findFromJWT();
        return ResponseEntity.ok(prenotazioneService.getPrenotazioniByUtente(richiedente));
    }
}
