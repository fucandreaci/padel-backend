package it.polimi.padel;

import it.polimi.padel.DTO.*;
import it.polimi.padel.model.Coupon;
import it.polimi.padel.model.Informazioni;
import it.polimi.padel.model.Ruolo;
import it.polimi.padel.model.Utente;
import it.polimi.padel.model.parsables.Contatto;
import it.polimi.padel.model.parsables.InfoVarie;
import it.polimi.padel.model.parsables.OrarioStruttura;
import it.polimi.padel.model.parsables.Regola;

import java.time.LocalTime;
import java.util.List;

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

    public static RequestCampoDto getRequestCampoDtoStub () {
        RequestCampoDto dto = new RequestCampoDto();
        dto.setNome("Campo 1");
        dto.setUrlImmagine("url immagine");
        return dto;
    }

    public static ResponseCampoDto getResponseCampoDtoStub () {
        ResponseCampoDto dto = new ResponseCampoDto();
        dto.setId(1);
        dto.setNome("Campo 1");
        dto.setUrlImmagine("url immagine");
        return dto;
    }

    public static Coupon getCouponStub () {
        Coupon coupon = new Coupon();
        coupon.setId(1);
        coupon.setCodice("codice");
        coupon.setValore(10.0f);
        coupon.setTipo(Coupon.TipoCoupon.EURO);
        return coupon;
    }

    public static RequestGenerateCouponDto getRequestGenerateCouponDtoStub () {
        RequestGenerateCouponDto dto = new RequestGenerateCouponDto();
        dto.setTipo(Coupon.TipoCoupon.EURO.toString());
        dto.setValore(10.0f);
        return dto;
    }

    public static Informazioni getInformazioniStub () {
        Informazioni informazioni = new Informazioni();
        informazioni.setId(1);
        informazioni.setChiave("Informazioni 1");
        informazioni.setValore("Descrizione 1");
        return informazioni;
    }

    public static InfoVarie getInfoVarieStub () {
        InfoVarie infoVarie = new InfoVarie();
        infoVarie.setNome("Informazioni 1");
        infoVarie.setDescrizione("Descrizione 1");
        return infoVarie;
    }

    public static Contatto getContattoStub () {
        Contatto contatto = new Contatto();
        contatto.setNome("Contatto 1");
        contatto.setDescrizione("Valore 1");
        return contatto;
    }

    public static OrarioStruttura getOrarioStrutturaStub () {
        OrarioStruttura orarioStruttura = new OrarioStruttura();
        orarioStruttura.setGiorno("Lunedì");
        orarioStruttura.setDalle(LocalTime.of(8, 0));
        orarioStruttura.setAlle(LocalTime.of(20, 0));
        return orarioStruttura;
    }

    public static Regola getRegolaStub () {
        Regola regola = new Regola();
        regola.setNome("Regola 1");
        regola.setDescrizione("Descrizione 1");
        return regola;
    }

    public static RequestRegoleDto getRequestRegoleDtoStub () {
        RequestRegoleDto dto = new RequestRegoleDto();
        dto.setRegole(List.of(getRegolaStub()));
        return dto;
    }

    public static RequestNewsDto getRequestNewsDtoStub () {
        RequestNewsDto dto = new RequestNewsDto();
        dto.setNews("New news");
        return dto;
    }

    public static RequestLezionePrivataDto getRequestLezionePrivataDtoStub () {
        RequestLezionePrivataDto dto = new RequestLezionePrivataDto();
        dto.setIdMaestro(1);
        dto.setIdCampo(1);
        return dto;
    }

    public static ResponsePrenotazioneWithTypeDto getResponsePrenotazioneWithTypeDtoStub () {
        ResponsePrenotazioneWithTypeDto dto = new ResponsePrenotazioneWithTypeDto();
        dto.setId(1);
        dto.setType(PrenotazioneType.LEZIONE_PRIVATA);
        return dto;
    }

    public static ResponseMaestroDto getResponseMaestroDtoStub () {
        ResponseMaestroDto dto = new ResponseMaestroDto();
        dto.setId(1);
        dto.setNome("Maestro 1");
        dto.setCognome("Cognome 1");
        return dto;
    }

    public static InviaMessaggioDto getInviaMessaggioDtoStub () {
        InviaMessaggioDto dto = new InviaMessaggioDto();
        dto.setIdDestinatario(1);
        dto.setMessaggio("Messaggio");
        return dto;
    }

    public static RequestPartitaDto getRequestPartitaDtoStub () {
        RequestPartitaDto dto = new RequestPartitaDto();
        dto.setIdCampo(1);
        return dto;
    }

    public static RequestInviaSegnalazioneDto getRequestInviaSegnalazioneDtoStub () {
        RequestInviaSegnalazioneDto dto = new RequestInviaSegnalazioneDto();
        dto.setIdChat("chatId");
        dto.setIdMessaggio("messageId");
        return dto;
    }

    public static ResponseSegnalazioneDto getResponseSegnalazioneDtoStub () {
        ResponseSegnalazioneDto dto = new ResponseSegnalazioneDto();
        dto.setId(1);
        dto.setChatId("chatId");
        dto.setMessaggioId("messageId");
        dto.setGestita(false);
        return dto;
    }

    public static RequestGestioneSegnalazioneDto getRequestGestioneSegnalazioneDtoStub () {
        RequestGestioneSegnalazioneDto dto = new RequestGestioneSegnalazioneDto();
        dto.setId(1);
        dto.setBlocco(true);
        return dto;
    }

    public static RequestCreaTorneoDto getRequestCreaTorneoDtoStub () {
        RequestCreaTorneoDto dto = new RequestCreaTorneoDto();
        dto.setDescrizione("Torneo 1");
        dto.setMaxPartecipanti(10);
        return dto;
    }

    public static ResponseTorneoDto getResponseTorneoDtoStub () {
        ResponseTorneoDto dto = new ResponseTorneoDto();
        dto.setId(1);
        dto.setDescrizione("Torneo 1");
        dto.setMaxPartecipanti(10);
        dto.setUtentePrenotato(false);
        dto.setNumPartecipanti(0);
        dto.setPrenotazioneAperta(true);
        return dto;
    }

    public static RequestIscrizioneTorneoDto getRequestIscrizioneTorneoDtoStub () {
        RequestIscrizioneTorneoDto dto = new RequestIscrizioneTorneoDto();
        dto.setIdTorneo(1);
        return dto;
    }

    public static RequestModificaTorneoDto getRequestModificaTorneoDtoStub () {
        RequestModificaTorneoDto dto = new RequestModificaTorneoDto();
        dto.setPrenotazioneAperta(true);
        dto.setDescrizione("Torneo 1");
        dto.setMaxPartecipanti(10);
        return dto;
    }
}
