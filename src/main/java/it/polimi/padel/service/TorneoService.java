package it.polimi.padel.service;

import it.polimi.padel.DTO.DtoManager;
import it.polimi.padel.DTO.RequestCreaTorneoDto;
import it.polimi.padel.DTO.ResponseTorneoDto;
import it.polimi.padel.exception.TorneoException;
import it.polimi.padel.model.Torneo;
import it.polimi.padel.model.Utente;
import it.polimi.padel.repository.TorneoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TorneoService {
    @Autowired
    private TorneoRepository torneoRepository;

    public Torneo findById (Integer id) {
        return torneoRepository.findById(id).orElse(null);
    }

    public Torneo creaTorneo (RequestCreaTorneoDto dto) {
        Torneo torneo = new Torneo();
        torneo.setMaxPartecipanti(dto.getMaxPartecipanti());
        torneo.setDescrizione(dto.getDescrizione());
        torneo.setPrenotazioneAperta(true);

        return torneoRepository.save(torneo);
    }

    public Torneo chiudiPrenotazione(Integer id) throws TorneoException {
        Torneo torneo = findById(id);

        if (torneo == null) {
            throw new TorneoException("Torneo non valido", HttpStatus.NOT_FOUND);
        }

        torneo.setPrenotazioneAperta(false);
        return torneoRepository.save(torneo);
    }

    public Torneo iscriviUtente (Integer idTorneo, Utente utente) throws TorneoException {
        Torneo torneo = findById(idTorneo);
        if (torneo == null) {
            throw new TorneoException("Torneo non valido", HttpStatus.NOT_FOUND);
        }

        if (torneo.getUtenti().stream().anyMatch(u -> u.getId()== utente.getId())) {
            throw new TorneoException("Sei già registrato a questo torneo", HttpStatus.BAD_REQUEST);
        }

        if (torneo.getMaxPartecipanti() <= torneo.getUtenti().size()) {
            throw new TorneoException("Il torneo è pieno", HttpStatus.BAD_REQUEST);
        }

        if (!torneo.getPrenotazioneAperta()) {
            throw new TorneoException("La prenotazione è chiusa", HttpStatus.BAD_REQUEST);
        }

        torneo.getUtenti().add(utente);
        return torneoRepository.save(torneo);
    }

    public Torneo rimuoviUtente (Integer idTorneo, Utente utente) throws TorneoException {
        Torneo torneo = findById(idTorneo);
        if (torneo == null) {
            throw new TorneoException("Torneo non valido", HttpStatus.NOT_FOUND);
        }

        if (torneo.getUtenti().stream().noneMatch(u -> u.getId()== utente.getId())) {
            throw new TorneoException("Non sei registrato a questo torneo", HttpStatus.BAD_REQUEST);
        }

        if (!torneo.getPrenotazioneAperta()) {
            throw new TorneoException("La prenotazione è chiusa", HttpStatus.BAD_REQUEST);
        }

        torneo.getUtenti().remove(utente);
        return torneoRepository.save(torneo);
    }

    public List<ResponseTorneoDto> getTornei (Utente richiedente) {
        List<Torneo> tornei = torneoRepository.findAll();
        return tornei.stream().map(t -> DtoManager.getResponseTorneoDtoFromTorneo(t, richiedente)).collect(Collectors.toList());
    }
}
