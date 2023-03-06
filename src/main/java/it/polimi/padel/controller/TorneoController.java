package it.polimi.padel.controller;

import it.polimi.padel.DTO.RequestCreaTorneoDto;
import it.polimi.padel.DTO.RequestIscrizioneTorneoDto;
import it.polimi.padel.DTO.RequestModificaTorneoDto;
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

    /**
     * Crea un nuovo torneo
     * @param dto
     * @return
     */
    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_' + T(it.polimi.padel.model.Ruolo).ADMIN)")
    public ResponseEntity<?> creaTorneo (@RequestBody @Valid RequestCreaTorneoDto dto) {
        return ResponseEntity.ok(torneoService.creaTorneo(dto));
    }

    /**
     * Ottieni la lista dei tornei
     * @return
     */
    @GetMapping("")
    public ResponseEntity<?> getTornei () {
        Utente richiedente = utenteService.findFromJWT();
        return ResponseEntity.ok(torneoService.getTornei(richiedente));
    }

    /**
     * Iscrivi un utente ad un torneo
     * @param dto
     * @return
     */
    @PostMapping("/iscriviti")
    public ResponseEntity<?> iscriviUtente (@RequestBody @Valid RequestIscrizioneTorneoDto dto) {
        Utente richiedente = utenteService.findFromJWT();
        try {
            torneoService.iscriviUtente(dto.getIdTorneo(), richiedente);
            return ResponseEntity.ok(null);
        } catch (TorneoException e) {
            return new ResponseEntity<>(e.getError(), e.getStatus());
        }
    }

    /**
     * Disiscrivi un utente da un torneo
     * @param dto
     * @return
     */
    @PostMapping("/rimuoviIscrizione")
    public ResponseEntity<?> rimuoviIscrizione (@RequestBody @Valid RequestIscrizioneTorneoDto dto) {
        Utente richiedente = utenteService.findFromJWT();
        try {
            torneoService.rimuoviUtente(dto.getIdTorneo(), richiedente);
            return ResponseEntity.ok(null);
        } catch (TorneoException e) {
            return new ResponseEntity<>(e.getError(), e.getStatus());
        }
    }

    /**
     * Modifica un torneo
     * @param id
     * @param dto
     * @return
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_' + T(it.polimi.padel.model.Ruolo).ADMIN)")
    public ResponseEntity<?> modificaTorneo (@PathVariable Integer id, @RequestBody @Valid RequestModificaTorneoDto dto) {
        try {
            return ResponseEntity.ok(torneoService.modificaTorneo(id, dto));
        } catch (TorneoException e) {
            return new ResponseEntity<>(e.getError(), e.getStatus());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_' + T(it.polimi.padel.model.Ruolo).ADMIN)")
    public ResponseEntity<?> eliminaTorneo (@PathVariable Integer id) {
        try {
            torneoService.deleteTorneo(id);
            return ResponseEntity.ok(null);
        } catch (TorneoException e) {
            return new ResponseEntity<>(e.getError(), e.getStatus());
        }
    }
}
