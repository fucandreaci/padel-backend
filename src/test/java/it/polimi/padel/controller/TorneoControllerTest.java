package it.polimi.padel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.padel.DTO.*;
import it.polimi.padel.Stub;
import it.polimi.padel.exception.SegnalazioneException;
import it.polimi.padel.exception.TorneoException;
import it.polimi.padel.exception.UserException;
import it.polimi.padel.model.Utente;
import it.polimi.padel.service.SegnalazioneService;
import it.polimi.padel.service.TorneoService;
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

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TorneoControllerTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private UtenteService utenteService;

    @MockBean
    private TorneoService torneoService;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void successCreaTorneo() throws Exception {
        RequestCreaTorneoDto requestDto = Stub.getRequestCreaTorneoDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        given(torneoService.creaTorneo(any())).willReturn(Stub.getResponseTorneoDtoStub());
        mvc.perform(MockMvcRequestBuilders.post("/tornei")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void successGetTornei() throws Exception {
        Utente user = Stub.getUtenteStub();
        given(utenteService.findFromJWT()).willReturn(user);
        given(torneoService.getTornei(any())).willReturn(List.of(Stub.getResponseTorneoDtoStub()));

        mvc.perform(MockMvcRequestBuilders.get("/tornei")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)));
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void successIscriviUtente() throws Exception {
        RequestIscrizioneTorneoDto requestDto = Stub.getRequestIscrizioneTorneoDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        Utente user = Stub.getUtenteStub();
        given(utenteService.findFromJWT()).willReturn(user);

        mvc.perform(MockMvcRequestBuilders.post("/tornei/iscriviti")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failIscriviUtenteTorneoNotFound() throws Exception {
        RequestIscrizioneTorneoDto requestDto = Stub.getRequestIscrizioneTorneoDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        Utente user = Stub.getUtenteStub();
        given(utenteService.findFromJWT()).willReturn(user);

        doThrow(new TorneoException("Torneo non valido", HttpStatus.NOT_FOUND)).when(torneoService).iscriviUtente(any(), any());
        mvc.perform(MockMvcRequestBuilders.post("/tornei/iscriviti")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failIscriviUtenteGiaRegistrato() throws Exception {
        RequestIscrizioneTorneoDto requestDto = Stub.getRequestIscrizioneTorneoDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        Utente user = Stub.getUtenteStub();
        given(utenteService.findFromJWT()).willReturn(user);

        doThrow(new TorneoException("Sei già registrato a questo torneo", HttpStatus.BAD_REQUEST)).when(torneoService).iscriviUtente(any(), any());
        mvc.perform(MockMvcRequestBuilders.post("/tornei/iscriviti")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failIscriviTorneoFull() throws Exception {
        RequestIscrizioneTorneoDto requestDto = Stub.getRequestIscrizioneTorneoDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        Utente user = Stub.getUtenteStub();
        given(utenteService.findFromJWT()).willReturn(user);

        doThrow(new TorneoException("Il torneo è pieno", HttpStatus.BAD_REQUEST)).when(torneoService).iscriviUtente(any(), any());
        mvc.perform(MockMvcRequestBuilders.post("/tornei/iscriviti")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failIscriviClosedPrenotazione() throws Exception {
        RequestIscrizioneTorneoDto requestDto = Stub.getRequestIscrizioneTorneoDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        Utente user = Stub.getUtenteStub();
        given(utenteService.findFromJWT()).willReturn(user);

        doThrow(new TorneoException("La prenotazione è chiusa", HttpStatus.BAD_REQUEST)).when(torneoService).iscriviUtente(any(), any());
        mvc.perform(MockMvcRequestBuilders.post("/tornei/iscriviti")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void successRimuoviUtente() throws Exception {
        RequestIscrizioneTorneoDto requestDto = Stub.getRequestIscrizioneTorneoDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        Utente user = Stub.getUtenteStub();
        given(utenteService.findFromJWT()).willReturn(user);

        mvc.perform(MockMvcRequestBuilders.post("/tornei/rimuoviIscrizione")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failRimuoviUtenteTorneoNotFound() throws Exception {
        RequestIscrizioneTorneoDto requestDto = Stub.getRequestIscrizioneTorneoDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        Utente user = Stub.getUtenteStub();
        given(utenteService.findFromJWT()).willReturn(user);

        doThrow(new TorneoException("Torneo non valido", HttpStatus.NOT_FOUND)).when(torneoService).rimuoviUtente(any(), any());
        mvc.perform(MockMvcRequestBuilders.post("/tornei/rimuoviIscrizione")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failRimuoviUtenteNonRegistrato() throws Exception {
        RequestIscrizioneTorneoDto requestDto = Stub.getRequestIscrizioneTorneoDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        Utente user = Stub.getUtenteStub();
        given(utenteService.findFromJWT()).willReturn(user);

        doThrow(new TorneoException("Non sei registrato a questo torneo", HttpStatus.BAD_REQUEST)).when(torneoService).rimuoviUtente(any(), any());
        mvc.perform(MockMvcRequestBuilders.post("/tornei/rimuoviIscrizione")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failRimuoviUtentePrenotazioneChiusa() throws Exception {
        RequestIscrizioneTorneoDto requestDto = Stub.getRequestIscrizioneTorneoDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        Utente user = Stub.getUtenteStub();
        given(utenteService.findFromJWT()).willReturn(user);

        doThrow(new TorneoException("La prenotazione è chiusa", HttpStatus.BAD_REQUEST)).when(torneoService).rimuoviUtente(any(), any());
        mvc.perform(MockMvcRequestBuilders.post("/tornei/rimuoviIscrizione")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void successModificaTorneo() throws Exception {
        RequestModificaTorneoDto requestDto = Stub.getRequestModificaTorneoDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        mvc.perform(MockMvcRequestBuilders.put("/tornei/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failModificaTorneoNotFound() throws Exception {
        RequestModificaTorneoDto requestDto = Stub.getRequestModificaTorneoDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        doThrow(new TorneoException("Torneo non valido", HttpStatus.NOT_FOUND)).when(torneoService).modificaTorneo(any(), any());
        mvc.perform(MockMvcRequestBuilders.put("/tornei/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failModificaTorneoErrorNumPartecipanti() throws Exception {
        RequestModificaTorneoDto requestDto = Stub.getRequestModificaTorneoDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        doThrow(new TorneoException("Il numero di partecipanti è maggiore del numero massimo", HttpStatus.BAD_REQUEST)).when(torneoService).modificaTorneo(any(), any());
        mvc.perform(MockMvcRequestBuilders.put("/tornei/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void successDeleteTorneo() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/tornei/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failDeleteTorneoNotFound() throws Exception {
        doThrow(new TorneoException("Torneo non valido", HttpStatus.NOT_FOUND)).when(torneoService).deleteTorneo(any());
        mvc.perform(MockMvcRequestBuilders.delete("/tornei/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
