package it.polimi.padel.repository;

import it.polimi.padel.model.Informazioni;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InformazioniRepository extends CrudRepository<Informazioni, Integer> {
    List<Informazioni> findAll();
    Informazioni findByChiave(String chiave);
}
