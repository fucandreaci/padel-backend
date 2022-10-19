package it.polimi.padel.repository;

import it.polimi.padel.model.Segnalazione;
import org.springframework.data.repository.CrudRepository;

public interface SegnalazioneRepository extends CrudRepository<Segnalazione, Integer> {
    Segnalazione getSegnalazioneByChatIdAndMessaggioId (String chatId, String messaggioId);
}
