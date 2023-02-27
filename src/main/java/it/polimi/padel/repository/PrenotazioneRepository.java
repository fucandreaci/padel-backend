package it.polimi.padel.repository;

import it.polimi.padel.model.Prenotazione;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PrenotazioneRepository extends CrudRepository<Prenotazione, Integer> {
    @Query("SELECT count(*) FROM Prenotazione p WHERE p.campo.id = ?1 AND (" +
            "(p.da <= ?2 AND p.a > ?2) OR (p.da < ?3 AND p.a >= ?3) OR (p.da >= ?2 AND p.a <= ?3)" +
            ")")
    Integer getNumPrenotazioniByDateIntervalAndCampo(Integer idCampo, LocalDateTime da, LocalDateTime a);

    @Query("SELECT p FROM Prenotazione p INNER JOIN LezionePrivata l ON l.id = p.lezionePrivata.id WHERE l.maestro.id = ?1 AND p.da <= ?3 AND p.a >= ?2")
    List<Prenotazione> isMaestroLibero (Integer idMaestro, LocalDateTime da, LocalDateTime a);

    @Query("SELECT p FROM Prenotazione p INNER JOIN LezionePrivata l ON l.id = p.lezionePrivata.id LEFT JOIN Coupon c ON p.id = c.id WHERE l.utente.id = ?1")
    List<Prenotazione> getLezioniPrivateByUtente (Integer idUtente);

    @Query("SELECT p FROM Prenotazione p INNER JOIN Partita l ON l.id = p.partita.id LEFT JOIN Coupon c ON p.id = c.id WHERE l.utente1.id = ?1")
    List<Prenotazione> getPartiteByUtente (Integer idUtente);
}
