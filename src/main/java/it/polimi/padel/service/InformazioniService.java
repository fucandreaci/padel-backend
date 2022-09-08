package it.polimi.padel.service;/*
 * File: InformazioniService
 * Project: Padel Backend
 * File Created: 06/09/22 - 16:44
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

import it.polimi.padel.model.Informazioni;
import it.polimi.padel.repository.InformazioniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class InformazioniService {
    @Autowired
    private InformazioniRepository informazioniRepository;

    /**
     * Ritorna tutte le informazioni
     * @return
     */
    public List<Informazioni> getInformazioni () {
        return informazioniRepository.findAll();
    }

    /**
     * Ritorna l'informazione con chiave specificata
     * @param chiave
     * @return
     */
    public Informazioni getByChiave (String chiave) {
        return informazioniRepository.findByChiave(chiave);
    }

    /**
     * Crea una nuova informazione
     * @param informazioni
     * @return
     */
    public Informazioni creaInformazione (Informazioni informazioni) {
        return informazioniRepository.save(informazioni);
    }

    /**
     * Crea una nuova informazione
     * @param chiave
     * @param valore
     * @return
     */
    public Informazioni creaInformazione (String chiave, String valore) {
        Informazioni info = new Informazioni();
        info.setChiave(chiave);
        info.setValore(valore);
        return creaInformazione(info);
    }
}
