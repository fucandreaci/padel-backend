package it.polimi.padel.controller;

import it.polimi.padel.DTO.RequestGestioneSegnalazioneDto;
import it.polimi.padel.DTO.RequestInviaSegnalazioneDto;
import it.polimi.padel.exception.SegnalazioneException;
import it.polimi.padel.service.SegnalazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/segnalazioni", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SegnalazioneController {
    @Autowired
    private SegnalazioneService segnalazioneService;

    /**
     * Endpoint per inviare una segnalazione
     * @param segnalazioneDto
     * @return
     */
    @PostMapping("")
    public ResponseEntity<?> segnalaUtente (@RequestBody @Valid RequestInviaSegnalazioneDto segnalazioneDto) {
        try {
            segnalazioneService.segnalaMessaggio(segnalazioneDto);
        } catch (SegnalazioneException e) {
            return new ResponseEntity<>(e.getError(), e.getStatus());
        }
        return ResponseEntity.ok(null);
    }

    /**
     * Endpoint per ottenere le segnalazioni non gestite
     * @return
     */
    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_' + T(it.polimi.padel.model.Ruolo).ADMIN)")
    public ResponseEntity<?> getSegnalazioniNonGestite () {
        try {
            return ResponseEntity.ok(segnalazioneService.getSegnalazioniNonGestite());
        } catch (SegnalazioneException e) {
            return new ResponseEntity<>(e.getError(), e.getStatus());
        }
    }

    @PostMapping("/gestisci")
    @PreAuthorize("hasRole('ROLE_' + T(it.polimi.padel.model.Ruolo).ADMIN)")
    public ResponseEntity<?> gestisciSegnalazione (@RequestBody RequestGestioneSegnalazioneDto gestioneSegnalazioneDto) {
        try {
            segnalazioneService.gestisciSegnalazione(gestioneSegnalazioneDto);
        } catch (SegnalazioneException e) {
            return new ResponseEntity<>(e.getError(), e.getStatus());
        }
        return ResponseEntity.ok(null);
    }
}
