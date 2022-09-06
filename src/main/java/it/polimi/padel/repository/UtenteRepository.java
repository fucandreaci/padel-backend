package it.polimi.padel.repository;

import it.polimi.padel.model.Utente;
import org.springframework.data.repository.CrudRepository;

public interface UtenteRepository extends CrudRepository<Utente, Integer> {
}
