package it.polimi.padel.repository;

import it.polimi.padel.model.Torneo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TorneoRepository extends CrudRepository<Torneo, Integer> {
    List<Torneo> findAll();
}
