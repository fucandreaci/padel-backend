package it.polimi.padel.repository;

import it.polimi.padel.model.Partita;
import it.polimi.padel.model.Utente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PartitaRepository extends CrudRepository<Partita, Integer> {
    List<Partita> findAllByUtente1(Utente utente);
}
