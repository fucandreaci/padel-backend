package it.polimi.padel.repository;

import it.polimi.padel.model.Maestro;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MaestroRepository extends CrudRepository<Maestro, Integer> {
    List<Maestro> findAll();
}
