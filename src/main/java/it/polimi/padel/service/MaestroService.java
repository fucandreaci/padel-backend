package it.polimi.padel.service;

import it.polimi.padel.DTO.DtoManager;
import it.polimi.padel.DTO.ResponseMaestroDto;
import it.polimi.padel.model.Maestro;
import it.polimi.padel.repository.MaestroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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
    public Maestro getMaestroById (Integer id) {
        return maestroRepository.findById(id).orElse(null);
    }

    /**
     * Ottieni la lista dei maestri disponibili
     * @return
     */
    public List<ResponseMaestroDto> getMaestri () {
        return maestroRepository.findAll().stream().map(DtoManager::getResponseMaestroDtoFromMaestro).collect(Collectors.toList());
    }
}
