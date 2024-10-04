package it.polimi.padel.repository;

import it.polimi.padel.model.Campo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CampoRepository extends CrudRepository<Campo, Integer> {
    List<Campo> findAll();
}
