package it.polimi.padel.service;

import it.polimi.padel.DTO.DtoManager;
import it.polimi.padel.DTO.RequestPrenotazioneDto;
import it.polimi.padel.DTO.ResponsePrenotazioneWithTypeDto;
import it.polimi.padel.exception.CampoNotFoundException;
import it.polimi.padel.exception.CampoOccupatoException;
import it.polimi.padel.exception.GenericException;
import it.polimi.padel.exception.StrutturaChiusaException;
import it.polimi.padel.model.Campo;
import it.polimi.padel.model.Prenotazione;
import it.polimi.padel.model.Utente;
import it.polimi.padel.model.parsables.OrarioStruttura;
import it.polimi.padel.repository.PrenotazioneRepository;
import it.polimi.padel.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private CampoService campoService;

    @Autowired
    private InformazioniService informazioniService;

    /**
     * Check se il campo specificato è libero
     * @param idCampo
     * @param da
     * @param a
     * @return
     */
    public boolean isCampoLibero (Integer idCampo, LocalDateTime da, LocalDateTime a) {
        return prenotazioneRepository.getNumPrenotazioniByDateIntervalAndCampo(idCampo, da, a) == 0;
    }

    /**
     * Check se il maestro è libero
     *
     * @param idMaestro
     * @param da
     * @param a
     * @return
     */
    public boolean isMaestroLibero (Integer idMaestro, LocalDateTime da, LocalDateTime a) {
        return !prenotazioneRepository.isMaestroLibero(idMaestro, da, a).isEmpty();
    }

    /**
     * Esegui la prenotazione di un campo
     * @param requestPrenotazioneDto
     * @return
     * @throws GenericException
     */
    public Prenotazione prenotaCampo (RequestPrenotazioneDto requestPrenotazioneDto) throws GenericException {
        Campo campo = campoService.getCampoById(requestPrenotazioneDto.getIdCampo());
        if (campo == null) {
            throw new CampoNotFoundException("Campo non trovato", HttpStatus.NOT_FOUND);
        }

        Prenotazione prenotazione = DtoManager.getPrenotazioneFromRequestPrenotazioneDto(requestPrenotazioneDto);
        prenotazione.setCampo(campo);

        if (!Utility.isValidDaADate(requestPrenotazioneDto.getDa(), requestPrenotazioneDto.getA())) {
            throw new GenericException(HttpStatus.BAD_REQUEST, "Date non valide");
        }

        if (!isCampoLibero(campo.getId(), prenotazione.getDa(), prenotazione.getA())) {
            throw new CampoOccupatoException("Campo non disponibile", HttpStatus.BAD_REQUEST);
        }

        LocalDateTime da;
        LocalDateTime a;
        try {
            da = Utility.getTimeZonedDate(requestPrenotazioneDto.getDa());
            a = Utility.getTimeZonedDate(requestPrenotazioneDto.getA());
        } catch (Exception e) {
            throw new GenericException(HttpStatus.BAD_REQUEST, "Date non valide");
        }

        DayOfWeek dayOfWeek = da.getDayOfWeek();
        int giornoDellaSettimana = dayOfWeek.getValue() - 1;

        OrarioStruttura orarioApertura = informazioniService.getOrariApertura().get(giornoDellaSettimana);
        if (!Utility.isStrutturaAperta(orarioApertura, da, a)) {
            throw new StrutturaChiusaException("La struttura è chiusa", HttpStatus.BAD_REQUEST);
        }

        //TODO: manage coupon

        return prenotazione;
    }

    /**
     * Ritorna la lista delle prenotazioni di un utente
     * @param utente
     * @return
     */
    public List<ResponsePrenotazioneWithTypeDto> getPrenotazioniByUtente (Utente utente) {
        List<Prenotazione> partite = prenotazioneRepository.getPartiteByUtente(utente.getId());
        List<Prenotazione> lezioni = prenotazioneRepository.getLezioniPrivateByUtente(utente.getId());


        List<Prenotazione> prenotazioni = Stream.of(partite, lezioni).flatMap(List::stream).collect(Collectors.toList());
        prenotazioni.forEach(prenotazione -> {
            prenotazione.setId(prenotazione.getId());
        });

        return prenotazioni.stream().map(DtoManager::getPreotazioneWithTypeDtoFromPrenotazione).collect(Collectors.toList());
    }

    /**
     * Salva la prenotazione sul db
     * @param prenotazione
     * @return
     */
    public Prenotazione savePrenotazione (Prenotazione prenotazione) {
        return prenotazioneRepository.save(prenotazione);
    }
}
