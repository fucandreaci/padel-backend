package it.polimi.padel.service;

import it.polimi.padel.DTO.DtoManager;
import it.polimi.padel.DTO.RequestPartitaDto;
import it.polimi.padel.DTO.ResponsePartitaDto;
import it.polimi.padel.DTO.ResponsePrenotazioneWithTypeDto;
import it.polimi.padel.exception.CouponException;
import it.polimi.padel.exception.GenericException;
import it.polimi.padel.model.Coupon;
import it.polimi.padel.model.Partita;
import it.polimi.padel.model.Prenotazione;
import it.polimi.padel.model.Utente;
import it.polimi.padel.repository.PartitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional(rollbackOn = GenericException.class)
public class PartitaService {
    @Autowired
    private PartitaRepository partitaRepository;

    @Autowired
    private CouponService couponService;

    @Autowired
    private PrenotazioneService prenotazioneService;

    /**
     * Esegui la prenotazione di una nuova partita specificando il campo e l'orario
     * @param requestPartitaDto
     * @param richiedente
     * @return
     * @throws GenericException
     */
    public ResponsePrenotazioneWithTypeDto prenotaPartita (RequestPartitaDto requestPartitaDto, Utente richiedente) throws GenericException {
        Partita partita = new Partita();
        partita.setUtente1(richiedente);
        partita = partitaRepository.save(partita);

        Prenotazione prenotazione = prenotazioneService.prenotaCampo(requestPartitaDto);
        prenotazione.setPartita(partita);
        prenotazione.setLezionePrivata(null);

        if (!requestPartitaDto.getCodiceCoupon().equals("")) {
            Coupon coupon = couponService.getCoupon(requestPartitaDto.getCodiceCoupon());
            if (coupon == null) {
                throw new CouponException("Il coupon inserito non esiste", HttpStatus.NOT_FOUND);
            }

            if (couponService.isCouponUtilizzato(requestPartitaDto.getCodiceCoupon())) {
                throw new CouponException("Il coupon inserito è già stato utilizzato", HttpStatus.BAD_REQUEST);
            }

            prenotazione.setCoupon(coupon);
        }
        prenotazione = prenotazioneService.savePrenotazione(prenotazione);

        return DtoManager.getPreotazioneWithTypeDtoFromPrenotazione(prenotazione);
    }

    /**
     * Ottieni la lista di partite prenotate da un utente
     * @param utente
     * @return
     */
    public List<Partita> getAllByUtente (Utente utente) {
        return partitaRepository.findAllByUtente1(utente);
    }
}
