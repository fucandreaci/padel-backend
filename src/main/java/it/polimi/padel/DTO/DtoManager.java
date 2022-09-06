package it.polimi.padel.DTO;

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
}
