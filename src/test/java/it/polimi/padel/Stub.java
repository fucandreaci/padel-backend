package it.polimi.padel;/*
 * File: stub
 * Project: Padel Backend
 * File Created: 25/04/23 - 16:53
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2023-2023 Andrea Fucci
 */

import it.polimi.padel.DTO.*;
import it.polimi.padel.model.Ruolo;
import it.polimi.padel.model.Utente;

public class Stub {
    public static RequestLoginDto getRequestLoginDtoStub() {
        RequestLoginDto dto = new RequestLoginDto();
        dto.setEmail("beatrice.romeo@polimi.it");
        dto.setPassword("password");
        return dto;
    }

    public static ResponseLoginDto getResponseLoginDtoStub() {
        ResponseLoginDto dto = new ResponseLoginDto();
        dto.setToken("token");
        dto.setRuolo(Ruolo.USER.toString());
        dto.setId(1);
        return dto;
    }

    public static RequestSignupDto getRequestSignupDtoStub() {
        RequestSignupDto dto = new RequestSignupDto();
        dto.setEmail("beatrice.romeo@polimi.it");
        dto.setPassword("password");
        dto.setNome("Beatrice");
        dto.setCognome("Romeo");
        return dto;
    }

    public static ResponseSignupDto getResponseSignupDtoStub() {
        ResponseSignupDto dto = new ResponseSignupDto();
        dto.setNome("Beatrice");
        dto.setCognome("Romeo");
        dto.setEmail("beatrice.romeo@polimi.it");
        return dto;
    }

    public static RequestAmiciziaDto getRequestAmiciziaDtoStub() {
        RequestAmiciziaDto dto = new RequestAmiciziaDto();
        dto.setIdUtente(1);
        return dto;
    }

    public static Utente getUtenteStub () {
        Utente utente = new Utente();
        utente.setId(1);
        utente.setNome("Beatrice");
        utente.setCognome("Romeo");
        utente.setEmail("beatrice.romeo@polimi.it");
        utente.setPassword("password");
        utente.setRuolo(Ruolo.USER);
        utente.setChatBloccata(false);
        return utente;
    }

    public static ResponseAmiciziaDto getResponseAmiciziaDtoStub() {
        ResponseAmiciziaDto dto = new ResponseAmiciziaDto();
        dto.setIdAmico(2);
        dto.setNomeAmico("Mario");
        dto.setCognomeAmico("Rossi");
        dto.setAccettata(true);
        return dto;
    }

    public static RequestConfermaAmiciziaDto getRequestConfermaAmiciziaDtoStub () {
        RequestConfermaAmiciziaDto dto = new RequestConfermaAmiciziaDto();
        dto.setIdAmico(2);
        dto.setConferma(true);
        return dto;
    }
}
