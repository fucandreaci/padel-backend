package it.polimi.padel.controller;

import it.polimi.padel.DTO.InviaMessaggioDto;
import it.polimi.padel.exception.UserException;
import it.polimi.padel.model.Utente;
import it.polimi.padel.service.FirebaseService;
import it.polimi.padel.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/messaggi", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MessaggioController {
    @Autowired
    private FirebaseService firebaseService;

    @Autowired
    private UtenteService utenteService;

    /**
     * Invia un nuovo messaggio ad un utente
     *
     * @param messaggioDto
     * @return
     */
    @PostMapping("/invia")
    public ResponseEntity<?> inviaMessaggio(@Valid @RequestBody InviaMessaggioDto messaggioDto) {
        Utente richiedente = utenteService.findFromJWT();
        Utente destinatario;

        if (richiedente.getChatBloccata() != null) {
            try {
                if (richiedente.getChatBloccata()) {
                    throw new UserException("La chat è bloccata", HttpStatus.BAD_REQUEST);
                }
            } catch (UserException e) {
                return new ResponseEntity<>(e.getError(), e.getStatus());
            }
        }

        try {
            destinatario = utenteService.findById(messaggioDto.getIdDestinatario());

            if (destinatario == null) {
                throw new UserException("Utente non trovato", HttpStatus.NOT_FOUND);
            }
            firebaseService.inviaMessaggio(richiedente, destinatario, messaggioDto.getMessaggio());
        } catch (UserException e) {
            return new ResponseEntity<>(e.getError(), e.getStatus());
        }
        return ResponseEntity.ok(null);
    }
}
