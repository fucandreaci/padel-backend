package it.polimi.padel.repository;

import it.polimi.padel.model.Utente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UtenteRepository extends CrudRepository<Utente, Integer> {
    Utente findByEmail(String email);

    @Query("SELECT u FROM Utente u WHERE u <> ?1 AND u.ruolo = 1 AND u.id NOT IN (SELECT a.utente2.id FROM Amici a WHERE a.utente1 = ?1) AND u.id NOT IN (SELECT a.utente1.id FROM Amici a WHERE a.utente2 = ?1)")
    List<Utente> findUtentiNonAmici(Utente user);
}
