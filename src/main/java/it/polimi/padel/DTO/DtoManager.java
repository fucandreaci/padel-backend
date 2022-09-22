package it.polimi.padel.DTO;

import it.polimi.padel.model.*;

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

    public static ResponseAmiciziaDto getResponseAmiciziaDtoFromAmici(Amici amici) {
        ResponseAmiciziaDto responseAmiciziaDto = new ResponseAmiciziaDto();
        responseAmiciziaDto.setNomeAmico(amici.getUtente2().getNome());
        responseAmiciziaDto.setCognomeAmico(amici.getUtente2().getCognome());
        responseAmiciziaDto.setIdAmico(amici.getUtente2().getId());
        responseAmiciziaDto.setAccettata(amici.getAccettata());
        return responseAmiciziaDto;
    }

    public static ResponsePrenotazioneWithTypeDto getPreotazioneWithTypeDtoFromPrenotazione(Prenotazione prenotazione) {
        ResponsePrenotazioneWithTypeDto responsePrenotazioneWithTypeDto = new ResponsePrenotazioneWithTypeDto();
        responsePrenotazioneWithTypeDto.setDa(prenotazione.getDa());
        responsePrenotazioneWithTypeDto.setA(prenotazione.getA());
        responsePrenotazioneWithTypeDto.setCampo(getCampoDtoFromCampo(prenotazione.getCampo()));
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
        campo.setImmagine(campo.getImmagine());
        return campo;
    }

    public static ResponseCampoDto getResponseCampoDtoFromCampo (Campo campo) {
        ResponseCampoDto responseCampoDto = new ResponseCampoDto();
        responseCampoDto.setId(campo.getId());
        responseCampoDto.setNome(campo.getNome());
        responseCampoDto.setUrlImmagine(responseCampoDto.getUrlImmagine());
        return responseCampoDto;
    }
}
