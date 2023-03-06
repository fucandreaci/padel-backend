package it.polimi.padel.controller;

import it.polimi.padel.DTO.RequestAmiciziaDto;
import it.polimi.padel.DTO.RequestConfermaAmiciziaDto;
import it.polimi.padel.exception.AmiciziaException;
import it.polimi.padel.exception.UserException;
import it.polimi.padel.model.Utente;
import it.polimi.padel.service.AmiciService;
import it.polimi.padel.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/amici", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AmiciController {
    @Autowired
    private AmiciService amiciService;

    @Autowired
    private UtenteService utenteService;

    /**
     * Invia una richiesta di amicizia
     * @param requestAmiciziaDto
     * @return
     */
    @PostMapping("/invia")
    public ResponseEntity<?> inviaRichiestaAmicizia (@Valid @RequestBody RequestAmiciziaDto requestAmiciziaDto) {
        //TODO: Da provare la richiesta
        Utente richiedente = utenteService.findFromJWT();
        try {
            return ResponseEntity.ok(amiciService.inviaRichiestaAmicizia(requestAmiciziaDto, richiedente));
        } catch (UserException | AmiciziaException e) {
            return new ResponseEntity<>(e.getError(), e.getStatus());
        }
    }

    /**
     * Conferma una richiesta di amicizia
     * @param confermaAmiciziaDto
     * @return
     */
    @PatchMapping("/conferma")
    public ResponseEntity<?> confermaRichiestaAmicizia (@Valid @RequestBody RequestConfermaAmiciziaDto confermaAmiciziaDto) {
        // TODO: provare la richiesta
        Utente richiedente = utenteService.findFromJWT();
        try {
            return ResponseEntity.ok(amiciService.accettaRichiestaAmicizia(confermaAmiciziaDto, richiedente));
        } catch (AmiciziaException e) {
            return new ResponseEntity<>(e.getError(), e.getStatus());
        }
    }

    /**
     * Ottiene la lista di amicizie in sospeso
     * @return
     */
    @GetMapping("/inSospeso")
    public ResponseEntity<?> getRichiesteInSospeso() {
        // TODO: provare la richiesta
        Utente richiedente = utenteService.findFromJWT();
        return ResponseEntity.ok(amiciService.getAmicizieInSospeso(richiedente));
    }

    /**
     * Ottiene la lista di amici
     * @return
     */
    @GetMapping("")
    public ResponseEntity<?> getAmici() {
        Utente richiedente = utenteService.findFromJWT();
        return ResponseEntity.ok(amiciService.getAmicizieAccettate(richiedente));
    }

    /**
     * Ottiene la lista di amici
     * @return
     */
    @GetMapping("/utentiDisponibili")
    public ResponseEntity<?> getNotAmici() {
        Utente richiedente = utenteService.findFromJWT();
        return ResponseEntity.ok(utenteService.getUtentiNotAmici(richiedente));
    }
}
