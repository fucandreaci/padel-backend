package it.polimi.padel.service;

import it.polimi.padel.DTO.*;
import it.polimi.padel.exception.AmiciziaException;
import it.polimi.padel.exception.UserException;
import it.polimi.padel.model.Amici;
import it.polimi.padel.model.Utente;
import it.polimi.padel.repository.AmiciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class AmiciService {
    @Autowired
    private AmiciRepository amiciRepository;

    @Autowired
    private UtenteService utenteService;

    /**
     * Controllo che l'utente non abbia già una richiesta di amicizia inviata all'utente specificato
     * @param utente1
     * @param utente2
     * @return
     */
    private boolean existAmicizia (Utente utente1, Utente utente2) {
        return amiciRepository.findByUtente1AndUtente2(utente1, utente2) != null;
    }

    /**
     * Invia una richiesta di amicizia
     * @param requestAmiciziaDto
     * @param richiedente
     * @throws UserException
     * @throws AmiciziaException
     */
    public ResponseAmiciziaDto inviaRichiestaAmicizia (RequestAmiciziaDto requestAmiciziaDto, Utente richiedente) throws UserException, AmiciziaException {
        Utente amicoDaAgg = utenteService.findById(requestAmiciziaDto.getIdUtente());
        if (amicoDaAgg == null) {
            throw new UserException("L'utente non esiste", HttpStatus.NOT_FOUND);
        }

        if (existAmicizia(richiedente, amicoDaAgg)) {
            throw new AmiciziaException("L'amicizia esiste già", HttpStatus.BAD_REQUEST);
        }

        if (amicoDaAgg.getId() == richiedente.getId()) {
            throw new AmiciziaException("Non puoi aggiungerti da solo", HttpStatus.BAD_REQUEST);
        }

        Amici amici = new Amici();
        amici.setUtente1(richiedente);
        amici.setUtente2(amicoDaAgg);
        amici.setUtente(richiedente);

        amici = amiciRepository.save(amici);
        return DtoManager.getResponseAmiciziaDtoFromAmici(amici, richiedente);
    }

    /**
     * Conferma una richiesta di amicizia specificando se accettare o rifiutare
     * @param confermaAmiciziaDto
     * @param richiedente
     * @throws AmiciziaException
     */
    public ResponseAmiciziaDto accettaRichiestaAmicizia (RequestConfermaAmiciziaDto confermaAmiciziaDto, Utente richiedente) throws AmiciziaException {
        Utente amico = utenteService.findById(confermaAmiciziaDto.getIdAmico());
        if (amico == null) {
            throw new AmiciziaException("L'utente non esiste", HttpStatus.NOT_FOUND);
        }

        if (!existAmicizia(amico, richiedente)) {
            throw new AmiciziaException("L'amicizia non esiste", HttpStatus.NOT_FOUND);
        }

        Amici amicizia = amiciRepository.findByUtente1AndUtente2(amico, richiedente);
        if (amicizia.getAccettata() != null) {
            throw new AmiciziaException("L'amicizia è già stata accettata/rifiutata", HttpStatus.BAD_REQUEST);
        }

        amicizia.setAccettata(confermaAmiciziaDto.getConferma());
        amicizia = amiciRepository.save(amicizia);

        return DtoManager.getResponseAmiciziaDtoFromAmici(amicizia, richiedente);
    }

    /**
     * Ritorna la lista delle richieste di amicizia ricevute
     * @param richiedente
     * @return
     */
    public List<ResponseAmiciziaDto> getAmicizieInSospeso (Utente richiedente) {
        List<Amici> amicizie = amiciRepository.findAllByUtente2AndAccettataIsNull(richiedente);
        List<ResponseAmiciziaDto> dtos = amicizie.stream().map(a -> DtoManager.getResponseAmiciziaDtoFromAmici(a, richiedente)).collect(Collectors.toList());
        return dtos;
    }

    /**
     * Ritorna la lista degli amici
     * @param richiedente
     * @return
     */
    public List<ResponseAmiciziaDto> getAmicizieAccettate (Utente richiedente) {
        // FIXME: il dto di output non è corretto. Far visualizzare solo l'altro utente e non l'utente stesso
        List<Amici> amicizie = amiciRepository.findAllByUtente1AndAccettataIsTrue(richiedente);
        List<Amici> amicizie2 = amiciRepository.findAllByUtente2AndAccettataIsNotFalse(richiedente);

        List<Amici> allAmicizie = Stream.of(amicizie, amicizie2).flatMap(List::stream).collect(Collectors.toList());

        return allAmicizie.stream().map(a -> DtoManager.getResponseAmiciziaDtoFromAmici(a, richiedente)).collect(Collectors.toList());
    }
}
