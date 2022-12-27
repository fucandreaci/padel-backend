package it.polimi.padel.service;

import it.polimi.padel.DTO.*;
import it.polimi.padel.exception.SegnalazioneException;
import it.polimi.padel.model.Segnalazione;
import it.polimi.padel.model.Utente;
import it.polimi.padel.repository.SegnalazioneRepository;
import it.polimi.padel.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class SegnalazioneService {
    @Autowired
    private SegnalazioneRepository segnalazioneRepository;

    @Autowired
    private FirebaseService firebaseService;

    @Autowired
    private UtenteService utenteService;

    public Segnalazione getById(Integer id) {
        return segnalazioneRepository.findById(id).orElse(null);
    }

    /**
     * Controlla se esiste già una segnalazione per uno specifico messaggio
     * @param chatId
     * @param messaggioId
     * @return
     */
    public boolean existSegnalazione (String chatId, String messaggioId) {
        Segnalazione segnalazione = segnalazioneRepository.getSegnalazioneByChatIdAndMessaggioId(chatId, messaggioId);
        return segnalazione != null;
    }

    /**
     * Invia la segnalazione di un messaggio
     * @param segnalazioneDto
     * @return
     * @throws SegnalazioneException
     */
    public Segnalazione segnalaMessaggio(RequestInviaSegnalazioneDto segnalazioneDto) throws SegnalazioneException {
        String chatId = segnalazioneDto.getIdChat();
        String messaggioId = segnalazioneDto.getIdMessaggio();

        if (existSegnalazione(chatId, messaggioId)) {
            throw new SegnalazioneException("Messaggio già segnalato", HttpStatus.BAD_REQUEST);
        }

        Segnalazione segnalazione = new Segnalazione();
        segnalazione.setChatId(chatId);
        segnalazione.setMessaggioId(messaggioId);
        segnalazione.setGestita(false);

        return segnalazioneRepository.save(segnalazione);
    }

    /**
     * Restituisce la lista delle segnalazioni non gestite
     * @return
     * @throws SegnalazioneException
     */
    public List<ResponseSegnalazioneDto> getSegnalazioniNonGestite() throws SegnalazioneException {
        List<Segnalazione> segnalazioni = segnalazioneRepository.getSegnalazionesByGestitaFalse();
        List<ResponseSegnalazioneDto> response = new ArrayList<>();

        for (Segnalazione segnalazione : segnalazioni) {
            Map<String, MessaggioDto> messaggi;
            try {
                messaggi = Utility.sortMessages(firebaseService.getMessaggiByChatId(segnalazione.getChatId()));
            } catch (ExecutionException | InterruptedException e) {
                throw new SegnalazioneException("Errore nel recupero dei messaggi", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.add(DtoManager.getResponseSegnalazioneDtoFromSegnalazione(segnalazione, Utility.filterMessages(messaggi, segnalazione.getMessaggioId(), 5)));
            //response.add(DtoManager.getResponseSegnalazioneDtoFromSegnalazione(segnalazione, null));
        }

        return response;
    }

    public void gestisciSegnalazione (RequestGestioneSegnalazioneDto segnalazioneDto) throws SegnalazioneException {
        Segnalazione segnalazione = getById(segnalazioneDto.getId());
        if (segnalazione == null) {
            throw new SegnalazioneException("Segnalazione non trovata", HttpStatus.NOT_FOUND);
        }

        if (segnalazione.getGestita()) {
            throw new SegnalazioneException("Segnalazione già gestita", HttpStatus.BAD_REQUEST);
        }

        Utente utente;
        try {
            utente = utenteService.findById(firebaseService.getUserIdSender(segnalazione.getChatId(), segnalazione.getMessaggioId()));
        } catch (ExecutionException | InterruptedException e) {
            throw new SegnalazioneException("Errore nel recupero dell'utente", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (utente == null) {
            throw new SegnalazioneException("Utente non trovato", HttpStatus.NOT_FOUND);
        }

        if (segnalazioneDto.getBlocco()) {
            utente.setChatBloccata(true);
            utenteService.updateUtente(utente);
        }
        segnalazione.setGestita(true);
        segnalazioneRepository.save(segnalazione);
    }
}
