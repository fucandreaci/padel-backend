package it.polimi.padel.service;

import it.polimi.padel.model.Maestro;
import it.polimi.padel.repository.MaestroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MaestroService {
    @Autowired
    private MaestroRepository maestroRepository;

    /**
     * Ritorna il maestro con l'id specificato
     * @param id
     * @return
     */
    public Maestro getMaestroById (int id) {
        return maestroRepository.findById(id).orElse(null);
    }
}
