package it.polimi.padel.service;

import it.polimi.padel.model.Utente;
import it.polimi.padel.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    public Utente findById(Integer id) {
        return utenteRepository.findById(id).orElse(null);
    }
}
