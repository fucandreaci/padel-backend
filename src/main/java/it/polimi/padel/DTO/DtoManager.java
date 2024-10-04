package it.polimi.padel.DTO;

import it.polimi.padel.model.*;

import java.util.List;

/**
 * Classe che gestisce la conversione tra oggetti di tipo model e DTO
 */
public class DtoManager {
    public static Utente getUtenteFromRequestSignupDto(RequestSignupDto requestSignupDto) {
        Utente utente = new Utente();
        utente.setNome(requestSignupDto.getNome());
        utente.setCognome(requestSignupDto.getCognome());
        utente.setEmail(requestSignupDto.getEmail());
        utente.setPassword(requestSignupDto.getPassword());
        return utente;
    }

    public static ResponseSignupDto getResponseSignupDtoFromUtente(Utente utente) {
        ResponseSignupDto responseSignupDto = new ResponseSignupDto();
        responseSignupDto.setNome(utente.getNome());
        responseSignupDto.setCognome(utente.getCognome());
        responseSignupDto.setEmail(utente.getEmail());
        return responseSignupDto;
    }

    public static Prenotazione getPrenotazioneFromRequestPrenotazioneDto(RequestPrenotazioneDto requestPrenotazioneDto) {
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setDa(requestPrenotazioneDto.getDa());
        prenotazione.setA(requestPrenotazioneDto.getA());
        return prenotazione;
    }

    public static ResponsePartitaDto getResponsePartitaDtoFromPartita(Prenotazione partita) {
        ResponsePartitaDto responsePartitaDto = new ResponsePartitaDto();
        responsePartitaDto.setDa(partita.getDa());
        responsePartitaDto.setA(partita.getA());
        responsePartitaDto.setCampo(getCampoDtoFromCampo(partita.getCampo()));
        return responsePartitaDto;
    }

    public static CampoDto getCampoDtoFromCampo(Campo campo) {
        CampoDto campoDto = new CampoDto();
        campoDto.setNome(campo.getNome());
        campoDto.setId(campo.getId());
        return campoDto;
    }

    public static ResponseLezionePrivataDto getResponseLezionePrivataDtoFromLezionePrivata(Prenotazione lezionePrivata) {
        ResponseLezionePrivataDto responseLezionePrivataDto = new ResponseLezionePrivataDto();
        responseLezionePrivataDto.setDa(lezionePrivata.getDa());
        responseLezionePrivataDto.setA(lezionePrivata.getA());
        responseLezionePrivataDto.setNomeMaestro(lezionePrivata.getLezionePrivata().getMaestro().getNome());
        responseLezionePrivataDto.setCognomeMaestro(lezionePrivata.getLezionePrivata().getMaestro().getCognome());
        responseLezionePrivataDto.setIdMaestro(lezionePrivata.getLezionePrivata().getMaestro().getId());
        responseLezionePrivataDto.setCampo(getCampoDtoFromCampo(lezionePrivata.getCampo()));
        return responseLezionePrivataDto;
    }

    public static ResponseAmiciziaDto getResponseAmiciziaDtoFromAmici(Amici amici, Utente richiedente) {
        ResponseAmiciziaDto responseAmiciziaDto = new ResponseAmiciziaDto();
        Utente u = amici.getUtente1().getId() == richiedente.getId() ? amici.getUtente2() : amici.getUtente1();

        responseAmiciziaDto.setNomeAmico(u.getNome());
        responseAmiciziaDto.setCognomeAmico(u.getCognome());
        responseAmiciziaDto.setIdAmico(u.getId());
        responseAmiciziaDto.setAccettata(amici.getAccettata());
        return responseAmiciziaDto;
    }

    public static ResponseAmiciziaDto getResponseAmiciziaDtoFromUtente(Utente utente) {
        ResponseAmiciziaDto responseAmiciziaDto = new ResponseAmiciziaDto();
        responseAmiciziaDto.setNomeAmico(utente.getNome());
        responseAmiciziaDto.setCognomeAmico(utente.getCognome());
        responseAmiciziaDto.setIdAmico(utente.getId());
        return responseAmiciziaDto;
    }

    public static CouponDto getCouponDtoFromCoupon(Coupon coupon) {
        if (coupon == null)
            return null;

        CouponDto couponDto = new CouponDto();
        couponDto.setCodice(coupon.getCodice());
        couponDto.setId(coupon.getId());
        couponDto.setTipo(coupon.getTipo());
        couponDto.setValore(coupon.getValore());
        return couponDto;
    }

    public static ResponsePrenotazioneWithTypeDto getPreotazioneWithTypeDtoFromPrenotazione(Prenotazione prenotazione) {
        ResponsePrenotazioneWithTypeDto responsePrenotazioneWithTypeDto = new ResponsePrenotazioneWithTypeDto();
        responsePrenotazioneWithTypeDto.setId(prenotazione.getId());
        responsePrenotazioneWithTypeDto.setDa(prenotazione.getDa());
        responsePrenotazioneWithTypeDto.setA(prenotazione.getA());
        responsePrenotazioneWithTypeDto.setCampo(getCampoDtoFromCampo(prenotazione.getCampo()));
        responsePrenotazioneWithTypeDto.setCoupon(getCouponDtoFromCoupon(prenotazione.getCoupon()));
        if (prenotazione.getLezionePrivata() != null) {
            responsePrenotazioneWithTypeDto.setType(PrenotazioneType.LEZIONE_PRIVATA);
            responsePrenotazioneWithTypeDto.setLezioniPrivate(getResponseLezionePrivataDtoFromLezionePrivata(prenotazione));
        } else {
            responsePrenotazioneWithTypeDto.setType(PrenotazioneType.PARTITA);
            responsePrenotazioneWithTypeDto.setPartite(getResponsePartitaDtoFromPartita(prenotazione));
        }
        return responsePrenotazioneWithTypeDto;
    }

    public static Campo getCampoFromRequestCampoDto (RequestCampoDto requestCampoDto) {
        Campo campo = new Campo();
        campo.setNome(requestCampoDto.getNome());
        campo.setImmagine(requestCampoDto.getUrlImmagine());
        return campo;
    }

    public static ResponseCampoDto getResponseCampoDtoFromCampo (Campo campo) {
        ResponseCampoDto responseCampoDto = new ResponseCampoDto();
        responseCampoDto.setId(campo.getId());
        responseCampoDto.setNome(campo.getNome());
        responseCampoDto.setUrlImmagine(campo.getImmagine());
        return responseCampoDto;
    }

    public static ResponseMaestroDto getResponseMaestroDtoFromMaestro(Maestro maestro) {
        ResponseMaestroDto responseMaestroDto = new ResponseMaestroDto();
        responseMaestroDto.setId(maestro.getId());
        responseMaestroDto.setNome(maestro.getNome());
        responseMaestroDto.setCognome(maestro.getCognome());
        return responseMaestroDto;
    }

    public static UtenteDto getUtenteDtoFromUtente(Utente utente) {
        UtenteDto utenteDto = new UtenteDto();
        utenteDto.setId(utente.getId());
        utenteDto.setNome(utente.getNome());
        utenteDto.setCognome(utente.getCognome());
        return utenteDto;
    }

    public static ResponseTorneoDto getResponseTorneoDtoFromTorneo(Torneo torneo, Utente richiedente) {
        ResponseTorneoDto responseTorneoDto = new ResponseTorneoDto();
        responseTorneoDto.setId(torneo.getId());
        responseTorneoDto.setNumPartecipanti(torneo.getUtenti().size());
        responseTorneoDto.setMaxPartecipanti(torneo.getMaxPartecipanti());
        responseTorneoDto.setPrenotazioneAperta(torneo.getPrenotazioneAperta());
        responseTorneoDto.setUtentePrenotato(torneo.getUtenti().stream().anyMatch(u -> u.getId() == richiedente.getId()));
        responseTorneoDto.setDescrizione(torneo.getDescrizione());

        return responseTorneoDto;
    }

    public static ResponseSegnalazioneDto getResponseSegnalazioneDtoFromSegnalazione(Segnalazione segnalazione, List<MessaggioDto> messaggi) {
        ResponseSegnalazioneDto responseSegnalazioneDto = new ResponseSegnalazioneDto();
        responseSegnalazioneDto.setId(segnalazione.getId());
        responseSegnalazioneDto.setGestita(segnalazione.getGestita());
        responseSegnalazioneDto.setChatId(segnalazione.getChatId());
        responseSegnalazioneDto.setMessaggioId(segnalazione.getMessaggioId());
        responseSegnalazioneDto.setMessaggi(messaggi);

        return responseSegnalazioneDto;
    }

    public static ResponseCouponDto getResponseCouponDtoFromCoupon(Coupon coupon) {
        ResponseCouponDto responseCouponDto = new ResponseCouponDto();
        responseCouponDto.setId(coupon.getId());
        responseCouponDto.setCodice(coupon.getCodice());
        responseCouponDto.setTipo(coupon.getTipo());
        responseCouponDto.setValore(coupon.getValore());
        responseCouponDto.setUtilizzato(coupon.getPrenotazione() != null);
        return responseCouponDto;
    }
}
