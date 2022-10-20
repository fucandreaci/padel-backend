package it.polimi.padel.controller;

import it.polimi.padel.DTO.RequestCreaTorneoDto;
import it.polimi.padel.DTO.RequestIscrizioneTorneoDto;
import it.polimi.padel.exception.TorneoException;
import it.polimi.padel.model.Utente;
import it.polimi.padel.service.TorneoService;
import it.polimi.padel.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/tornei", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TorneoController {
    @Autowired
    private TorneoService torneoService;

    @Autowired
    private UtenteService utenteService;

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_' + T(it.polimi.padel.model.Ruolo).ADMIN)")
    public ResponseEntity<?> creaTorneo (@RequestBody @Valid RequestCreaTorneoDto dto) {
        torneoService.creaTorneo(dto);
        return ResponseEntity.ok(null);
    }

    @GetMapping("")
    public ResponseEntity<?> getTornei () {
        Utente richiedente = utenteService.findFromJWT();
        return ResponseEntity.ok(torneoService.getTornei(richiedente));
    }

    @PostMapping("/iscriviti")
    public ResponseEntity<?> iscriviUtente (@RequestBody @Valid RequestIscrizioneTorneoDto dto) {
        Utente richiedente = utenteService.findFromJWT();
        try {
            torneoService.iscriviUtente(dto.getIdTorneo(), richiedente);
            return ResponseEntity.ok(null);
        } catch (TorneoException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatus());
        }
    }

    @PostMapping("/rimuoviIscrizione")
    public ResponseEntity<?> rimuoviIscrizione (@RequestBody @Valid RequestIscrizioneTorneoDto dto) {
        Utente richiedente = utenteService.findFromJWT();
        try {
            torneoService.rimuoviUtente(dto.getIdTorneo(), richiedente);
            return ResponseEntity.ok(null);
        } catch (TorneoException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatus());
        }
    }
}
