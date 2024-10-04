package it.polimi.padel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.padel.DTO.RequestLezionePrivataDto;
import it.polimi.padel.Stub;
import it.polimi.padel.exception.GenericException;
import it.polimi.padel.model.Utente;
import it.polimi.padel.service.LezionePrivataService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LezionePrivataControllerTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private UtenteService utenteService;

    @MockBean
    private LezionePrivataService lezionePrivataService;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void successPrenotaLezione() throws Exception {
        RequestLezionePrivataDto requestDto = Stub.getRequestLezionePrivataDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        Utente user = Stub.getUtenteStub();
        given(utenteService.findFromJWT()).willReturn(user);
        given(lezionePrivataService.prenotaLezionePrivata(any(), any())).willReturn(Stub.getResponsePrenotazioneWithTypeDtoStub());

        mvc.perform(MockMvcRequestBuilders.post("/lezioniprivate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failPrenotaLezione() throws Exception {
        RequestLezionePrivataDto requestDto = Stub.getRequestLezionePrivataDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        Utente user = Stub.getUtenteStub();
        given(utenteService.findFromJWT()).willReturn(user);
        doThrow(new GenericException(HttpStatus.INTERNAL_SERVER_ERROR, "")).when(lezionePrivataService).prenotaLezionePrivata(any(), any());

        mvc.perform(MockMvcRequestBuilders.post("/lezioniprivate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isBadRequest());
    }

}
