package it.polimi.padel.controller;

import it.polimi.padel.DTO.RequestInviaSegnalazioneDto;
import it.polimi.padel.exception.SegnalazioneException;
import it.polimi.padel.service.SegnalazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
            return new ResponseEntity<>(e.getMessage(), e.getStatus());
        }
        return ResponseEntity.ok(null);
    }
}
