package it.polimi.padel.service;

import it.polimi.padel.DTO.DtoManager;
import it.polimi.padel.DTO.RequestLezionePrivataDto;
import it.polimi.padel.DTO.ResponsePrenotazioneWithTypeDto;
import it.polimi.padel.exception.CouponException;
import it.polimi.padel.exception.GenericException;
import it.polimi.padel.exception.MaestroNotFoundException;
import it.polimi.padel.model.*;
import it.polimi.padel.repository.LezionePrivataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class LezionePrivataService {
    @Autowired
    private LezionePrivataRepository lezionePrivataRepository;

    @Autowired
    private PrenotazioneService prenotazioneService;

    @Autowired
    private MaestroService maestroService;

    @Autowired
    private CouponService couponService;

    /**
     * Prenota una lezione privata
     * @param lezionePrivataDto
     * @param richiedente
     * @return
     * @throws GenericException
     */
    public ResponsePrenotazioneWithTypeDto prenotaLezionePrivata (RequestLezionePrivataDto lezionePrivataDto, Utente richiedente) throws GenericException {
        Maestro maestro = maestroService.getMaestroById(lezionePrivataDto.getIdMaestro());
        if (maestro == null) {
            throw new MaestroNotFoundException("Il maestro non esiste", HttpStatus.NOT_FOUND);
        }

        if (prenotazioneService.isMaestroLibero(maestro.getId(), lezionePrivataDto.getDa(), lezionePrivataDto.getA())) {
            throw new GenericException(HttpStatus.BAD_REQUEST, "Il maestro è già impegnato");
        }

        LezionePrivata lezionePrivata = new LezionePrivata();
        lezionePrivata.setUtente(richiedente);
        lezionePrivata.setMaestro(maestro);
        lezionePrivata = lezionePrivataRepository.save(lezionePrivata);

        Prenotazione prenotazione = prenotazioneService.prenotaCampo(lezionePrivataDto);
        prenotazione.setLezionePrivata(lezionePrivata);

        if (!lezionePrivataDto.getCodiceCoupon().equals("")) {
            Coupon coupon = couponService.getCoupon(lezionePrivataDto.getCodiceCoupon());
            if (coupon == null) {
                throw new CouponException("Il coupon inserito non esiste", HttpStatus.NOT_FOUND);
            }

            if (couponService.isCouponUtilizzato(lezionePrivataDto.getCodiceCoupon())) {
                throw new CouponException("Il coupon inserito è già stato utilizzato", HttpStatus.BAD_REQUEST);
            }

            prenotazione.setCoupon(coupon);
        }


        prenotazione = prenotazioneService.savePrenotazione(prenotazione);

        return DtoManager.getPreotazioneWithTypeDtoFromPrenotazione(prenotazione);
    }
}
