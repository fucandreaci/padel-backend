package it.polimi.padel.repository;

import it.polimi.padel.model.LezionePrivata;
import it.polimi.padel.model.Utente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LezionePrivataRepository extends CrudRepository<LezionePrivata, Integer> {
    List<LezionePrivata> findAllByUtente(Utente utente);
}
