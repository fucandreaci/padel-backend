package it.polimi.padel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.padel.DTO.RequestGestioneSegnalazioneDto;
import it.polimi.padel.DTO.RequestInviaSegnalazioneDto;
import it.polimi.padel.DTO.RequestPartitaDto;
import it.polimi.padel.Stub;
import it.polimi.padel.exception.GenericException;
import it.polimi.padel.exception.SegnalazioneException;
import it.polimi.padel.model.Utente;
import it.polimi.padel.service.PrenotazioneService;
import it.polimi.padel.service.SegnalazioneService;
import it.polimi.padel.service.UtenteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SegnalazioneControllerTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private SegnalazioneService segnalazioneService;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void successSegnalaUtente() throws Exception {
        RequestInviaSegnalazioneDto requestDto = Stub.getRequestInviaSegnalazioneDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        mvc.perform(MockMvcRequestBuilders.post("/segnalazioni")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failSegnalaUtente() throws Exception {
        RequestInviaSegnalazioneDto requestDto = Stub.getRequestInviaSegnalazioneDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        doThrow(new SegnalazioneException("Messaggio già segnalato", HttpStatus.BAD_REQUEST)).when(segnalazioneService).segnalaMessaggio(any());
        mvc.perform(MockMvcRequestBuilders.post("/segnalazioni")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void successGetSegnalazioniNonGestite() throws Exception {
        given(segnalazioneService.getSegnalazioniNonGestite()).willReturn(List.of(Stub.getResponseSegnalazioneDtoStub()));
        mvc.perform(MockMvcRequestBuilders.get("/segnalazioni")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failGetSegnalazioniNonGestite() throws Exception {
        doThrow(new SegnalazioneException("Errore nel recupero dei messaggi", HttpStatus.INTERNAL_SERVER_ERROR)).when(segnalazioneService).getSegnalazioniNonGestite();
        mvc.perform(MockMvcRequestBuilders.get("/segnalazioni")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }


    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void successGestisciSegnalazione() throws Exception {
        RequestGestioneSegnalazioneDto requestDto = Stub.getRequestGestioneSegnalazioneDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        mvc.perform(MockMvcRequestBuilders.post("/segnalazioni/gestisci")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failGestisciSegnalazioneNotFound() throws Exception {
        RequestInviaSegnalazioneDto requestDto = Stub.getRequestInviaSegnalazioneDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        doThrow(new SegnalazioneException("Segnalazione non trovata", HttpStatus.NOT_FOUND)).when(segnalazioneService).gestisciSegnalazione(any());
        mvc.perform(MockMvcRequestBuilders.post("/segnalazioni/gestisci")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failGestisciSegnalazioneAlreadyGestita() throws Exception {
        RequestInviaSegnalazioneDto requestDto = Stub.getRequestInviaSegnalazioneDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        doThrow(new SegnalazioneException("Segnalazione già gestita", HttpStatus.BAD_REQUEST)).when(segnalazioneService).gestisciSegnalazione(any());
        mvc.perform(MockMvcRequestBuilders.post("/segnalazioni/gestisci")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failGestisciSegnalazioneErroreUtente() throws Exception {
        RequestInviaSegnalazioneDto requestDto = Stub.getRequestInviaSegnalazioneDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        doThrow(new SegnalazioneException("Errore nel recupero dell'utente", HttpStatus.INTERNAL_SERVER_ERROR)).when(segnalazioneService).gestisciSegnalazione(any());
        mvc.perform(MockMvcRequestBuilders.post("/segnalazioni/gestisci")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failGestisciSegnalazioneUtenteNotFound() throws Exception {
        RequestInviaSegnalazioneDto requestDto = Stub.getRequestInviaSegnalazioneDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        doThrow(new SegnalazioneException("Utente non trovato", HttpStatus.NOT_FOUND)).when(segnalazioneService).gestisciSegnalazione(any());
        mvc.perform(MockMvcRequestBuilders.post("/segnalazioni/gestisci")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isNotFound());
    }
}
