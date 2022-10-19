package it.polimi.padel.service;

import it.polimi.padel.DTO.RequestInviaSegnalazioneDto;
import it.polimi.padel.exception.SegnalazioneException;
import it.polimi.padel.model.Segnalazione;
import it.polimi.padel.repository.SegnalazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SegnalazioneService {
    @Autowired
    private SegnalazioneRepository segnalazioneRepository;

    public boolean existSegnalazione (String chatId, String messaggioId) {
        Segnalazione segnalazione = segnalazioneRepository.getSegnalazioneByChatIdAndMessaggioId(chatId, messaggioId);
        return segnalazione != null;
    }

    public Segnalazione segnalaMessaggio(RequestInviaSegnalazioneDto segnalazioneDto) throws SegnalazioneException {
        String chatId = segnalazioneDto.getIdChat();
        String messaggioId = segnalazioneDto.getIdMessaggio();

        if (existSegnalazione(chatId, messaggioId)) {
            throw new SegnalazioneException("Messaggio già segnalato", HttpStatus.BAD_REQUEST);
        }

        Segnalazione segnalazione = new Segnalazione();
        segnalazione.setChatId(chatId);
        segnalazione.setMessaggioId(messaggioId);

        return segnalazioneRepository.save(segnalazione);
    }
}
