package it.polimi.padel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.padel.DTO.RequestAmiciziaDto;
import it.polimi.padel.DTO.RequestConfermaAmiciziaDto;
import it.polimi.padel.DTO.ResponseAmiciziaDto;
import it.polimi.padel.Stub;
import it.polimi.padel.exception.AmiciziaException;
import it.polimi.padel.exception.UserException;
import it.polimi.padel.model.Utente;
import it.polimi.padel.service.AmiciService;
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
public class AmiciControllerTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private AmiciService amiciService;

    @MockBean
    private UtenteService utenteService;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }
    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void successInviaRichiestaAmicizia() throws Exception {
        Utente user = Stub.getUtenteStub();
        given(utenteService.findFromJWT()).willReturn(user);

        RequestAmiciziaDto requestDto = Stub.getRequestAmiciziaDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        given(amiciService.inviaRichiestaAmicizia(any(), any())).willReturn(Stub.getResponseAmiciziaDtoStub());
        mvc.perform(MockMvcRequestBuilders.post("/amici/invia")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomeAmico", is("Mario")))
                .andExpect(jsonPath("$.cognomeAmico", is("Rossi")))
                .andExpect(jsonPath("$.idAmico", is(2)))
                .andExpect(jsonPath("$.accettata", is(true)));

    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failInviaRichiestaAmiciziaAmicoNonEsiste() throws Exception {
        Utente user = Stub.getUtenteStub();
        given(utenteService.findFromJWT()).willReturn(user);

        RequestAmiciziaDto requestDto = Stub.getRequestAmiciziaDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        doThrow(new UserException("L'utente non esiste", HttpStatus.NOT_FOUND)).when(amiciService).inviaRichiestaAmicizia(any(), any());
        mvc.perform(MockMvcRequestBuilders.post("/amici/invia")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isNotFound());

    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failInviaRichiestaAmiciziaAmiciziaEsistente() throws Exception {
        Utente user = Stub.getUtenteStub();
        given(utenteService.findFromJWT()).willReturn(user);

        RequestAmiciziaDto requestDto = Stub.getRequestAmiciziaDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        doThrow(new AmiciziaException("L'amicizia esiste già", HttpStatus.BAD_REQUEST)).when(amiciService).inviaRichiestaAmicizia(any(), any());
        mvc.perform(MockMvcRequestBuilders.post("/amici/invia")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failInviaRichiestaAmiciziaRichiestaAMe() throws Exception {
        Utente user = Stub.getUtenteStub();
        given(utenteService.findFromJWT()).willReturn(user);

        RequestAmiciziaDto requestDto = Stub.getRequestAmiciziaDtoStub();
        requestDto.setIdUtente(1);
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        doThrow(new AmiciziaException("Non puoi aggiungerti da solo", HttpStatus.BAD_REQUEST)).when(amiciService).inviaRichiestaAmicizia(any(), any());
        mvc.perform(MockMvcRequestBuilders.post("/amici/invia")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void successConfermaRichiestaAmicizia() throws Exception {
        Utente user = Stub.getUtenteStub();
        given(utenteService.findFromJWT()).willReturn(user);

        RequestConfermaAmiciziaDto requestDto = Stub.getRequestConfermaAmiciziaDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        given(amiciService.accettaRichiestaAmicizia(any(), any())).willReturn(Stub.getResponseAmiciziaDtoStub());
        mvc.perform(MockMvcRequestBuilders.patch("/amici/conferma")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomeAmico", is("Mario")))
                .andExpect(jsonPath("$.cognomeAmico", is("Rossi")))
                .andExpect(jsonPath("$.idAmico", is(2)))
                .andExpect(jsonPath("$.accettata", is(true)));

    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failConfermaRichiestaAmiciziaUtenteNonEsiste() throws Exception {
        Utente user = Stub.getUtenteStub();
        given(utenteService.findFromJWT()).willReturn(user);

        RequestConfermaAmiciziaDto requestDto = Stub.getRequestConfermaAmiciziaDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        doThrow(new AmiciziaException("L'utente non esiste", HttpStatus.NOT_FOUND)).when(amiciService).accettaRichiestaAmicizia(any(), any());
        mvc.perform(MockMvcRequestBuilders.patch("/amici/conferma")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isNotFound());

    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failConfermaRichiestaAmiciziaAmiciziaInesistente() throws Exception {
        Utente user = Stub.getUtenteStub();
        given(utenteService.findFromJWT()).willReturn(user);

        RequestConfermaAmiciziaDto requestDto = Stub.getRequestConfermaAmiciziaDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        doThrow(new AmiciziaException("L'amicizia non esiste", HttpStatus.NOT_FOUND)).when(amiciService).accettaRichiestaAmicizia(any(), any());
        mvc.perform(MockMvcRequestBuilders.patch("/amici/conferma")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isNotFound());

    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failConfermaRichiestaAmiciziaGiaApprovata() throws Exception {
        Utente user = Stub.getUtenteStub();
        given(utenteService.findFromJWT()).willReturn(user);

        RequestConfermaAmiciziaDto requestDto = Stub.getRequestConfermaAmiciziaDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        doThrow(new AmiciziaException("L'amicizia è già stata accettata/rifiutata", HttpStatus.BAD_REQUEST)).when(amiciService).accettaRichiestaAmicizia(any(), any());
        mvc.perform(MockMvcRequestBuilders.patch("/amici/conferma")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void getRichiesteAmiciziaInSospeso() throws Exception {
        Utente user = Stub.getUtenteStub();
        given(utenteService.findFromJWT()).willReturn(user);

        List<ResponseAmiciziaDto> response = List.of(Stub.getResponseAmiciziaDtoStub());
        given(amiciService.getAmicizieInSospeso(any())).willReturn(response);
        mvc.perform(MockMvcRequestBuilders.get("/amici/inSospeso")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nomeAmico", is("Mario")))
                .andExpect(jsonPath("$[0].cognomeAmico", is("Rossi")))
                .andExpect(jsonPath("$[0].idAmico", is(2)))
                .andExpect(jsonPath("$[0].accettata", is(true)));

    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void getAmiciEffettivi() throws Exception {
        Utente user = Stub.getUtenteStub();
        given(utenteService.findFromJWT()).willReturn(user);

        List<ResponseAmiciziaDto> response = List.of(Stub.getResponseAmiciziaDtoStub());
        given(amiciService.getAmicizieAccettate(any())).willReturn(response);
        mvc.perform(MockMvcRequestBuilders.get("/amici")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nomeAmico", is("Mario")))
                .andExpect(jsonPath("$[0].cognomeAmico", is("Rossi")))
                .andExpect(jsonPath("$[0].idAmico", is(2)))
                .andExpect(jsonPath("$[0].accettata", is(true)));

    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void getUtentiNonAmici() throws Exception {
        Utente user = Stub.getUtenteStub();
        given(utenteService.findFromJWT()).willReturn(user);

        List<ResponseAmiciziaDto> response = List.of(Stub.getResponseAmiciziaDtoStub());
        given(utenteService.getUtentiNotAmici(any())).willReturn(response);
        mvc.perform(MockMvcRequestBuilders.get("/amici/utentiDisponibili")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nomeAmico", is("Mario")))
                .andExpect(jsonPath("$[0].cognomeAmico", is("Rossi")))
                .andExpect(jsonPath("$[0].idAmico", is(2)))
                .andExpect(jsonPath("$[0].accettata", is(true)));

    }
}
