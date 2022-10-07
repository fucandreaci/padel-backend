package it.polimi.padel.repository;

import it.polimi.padel.model.Amici;
import it.polimi.padel.model.Utente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AmiciRepository extends CrudRepository<Amici, Integer> {
    Amici findByUtente1AndUtente2 (Utente utente1, Utente utente2);
    List<Amici> findAllByUtente2AndAccettataIsNull(Utente utente1);
    @Query("SELECT a FROM Amici a WHERE a.utente1 = ?1 AND a.accettata = true")
    List<Amici> findAllByUtente1AndAccettataIsTrue(Utente utente1);
    @Query("SELECT a FROM Amici a WHERE a.utente2 = ?1 AND (a.accettata = true OR a.accettata IS NULL)")
    List<Amici> findAllByUtente2AndAccettataIsNotFalse(Utente utente1);
}
