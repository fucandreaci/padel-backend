package it.polimi.padel.service;

import it.polimi.padel.DTO.DtoManager;
import it.polimi.padel.DTO.RequestCampoDto;
import it.polimi.padel.DTO.ResponseCampoDto;
import it.polimi.padel.exception.CampoNotFoundException;
import it.polimi.padel.model.Campo;
import it.polimi.padel.repository.CampoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CampoService {
    @Autowired
    private CampoRepository campoRepository;

    /**
     * Ritorna il campo con l'id specificato
     * @param id
     * @return
     */
    public Campo getCampoById (Integer id) {
        return campoRepository.findById(id).orElse(null);
    }

    /**
     * Aggiungi un nuovo campo
     * @param requestCampoDto
     * @return
     */
    public ResponseCampoDto aggiungiCampo (RequestCampoDto requestCampoDto) {
        Campo campo = DtoManager.getCampoFromRequestCampoDto(requestCampoDto);
        campo = campoRepository.save(campo);
        return DtoManager.getResponseCampoDtoFromCampo(campo);
    }

    /**
     * Elimina un campo
     * @param id
     * @throws CampoNotFoundException
     */
    public void deleteCampo (Integer id) throws CampoNotFoundException {
        Campo campo = getCampoById(id);
        if (campo == null) {
            throw new CampoNotFoundException("Il campo non esiste", HttpStatus.NOT_FOUND);
        }

        campoRepository.delete(campo);
    }

    /**
     * Ritorna tutti i campi
     * @return
     */
    public List<ResponseCampoDto> getCampi () {
        return campoRepository.findAll().stream().map(DtoManager::getResponseCampoDtoFromCampo).collect(Collectors.toList());
    }
}
