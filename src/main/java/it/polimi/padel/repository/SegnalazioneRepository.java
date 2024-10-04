package it.polimi.padel.repository;

import it.polimi.padel.model.Segnalazione;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SegnalazioneRepository extends CrudRepository<Segnalazione, Integer> {
    Segnalazione getSegnalazioneByChatIdAndMessaggioId (String chatId, String messaggioId);
    List<Segnalazione> getSegnalazionesByGestitaFalse();
}
