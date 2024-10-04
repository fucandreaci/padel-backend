package it.polimi.padel.controller;

import it.polimi.padel.DTO.RequestLezionePrivataDto;
import it.polimi.padel.exception.GenericException;
import it.polimi.padel.model.Utente;
import it.polimi.padel.service.LezionePrivataService;
import it.polimi.padel.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/lezioniprivate", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LezionePrivataController {
    @Autowired
    private LezionePrivataService lezionePrivataService;

    @Autowired
    private UtenteService utenteService;

    /**
     * Endpoint per prenotare una lezione privata
     * @param lezionePrivataDto
     * @return
     */
    @PostMapping("")
    public ResponseEntity<?> prenotaLezionePrivata(@Valid @RequestBody RequestLezionePrivataDto lezionePrivataDto) {
        Utente richiedente = utenteService.findFromJWT();

        try {
            return ResponseEntity.ok(lezionePrivataService.prenotaLezionePrivata(lezionePrivataDto, richiedente));
        } catch (GenericException e) {
            return new ResponseEntity<>(e.getError(), e.getStatus());
        }
    }
}
