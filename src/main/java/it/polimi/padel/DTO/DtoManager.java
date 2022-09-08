package it.polimi.padel.DTO;

import it.polimi.padel.model.Campo;
import it.polimi.padel.model.Partita;
import it.polimi.padel.model.Prenotazione;
import it.polimi.padel.model.Utente;

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
        responsePartitaDto.setNomeAvversario(partita.getPartita().getUtente2().getNome());
        responsePartitaDto.setCognomeAvversario(partita.getPartita().getUtente2().getCognome());
        responsePartitaDto.setIdAvversario(partita.getPartita().getUtente2().getId());
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
}
