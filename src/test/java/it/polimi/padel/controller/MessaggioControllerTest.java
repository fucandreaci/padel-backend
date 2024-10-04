package it.polimi.padel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.padel.DTO.InviaMessaggioDto;
import it.polimi.padel.Stub;
import it.polimi.padel.model.Utente;
import it.polimi.padel.service.FirebaseService;
import it.polimi.padel.service.MaestroService;
import it.polimi.padel.service.UtenteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessaggioControllerTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private UtenteService utenteService;

    @MockBean
    private FirebaseService firebaseService;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void successInviaMessaggio() throws Exception {
        InviaMessaggioDto requestDto = Stub.getInviaMessaggioDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        Utente user = Stub.getUtenteStub();
        given(utenteService.findFromJWT()).willReturn(user);

        Utente destinatario = Stub.getUtenteStub();
        given(utenteService.findById(requestDto.getIdDestinatario())).willReturn(destinatario);


        mvc.perform(MockMvcRequestBuilders.post("/messaggi/invia")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failInviaMessaggioChatBloccata() throws Exception {
        InviaMessaggioDto requestDto = Stub.getInviaMessaggioDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        Utente user = Stub.getUtenteStub();
        user.setChatBloccata(true);
        given(utenteService.findFromJWT()).willReturn(user);

        Utente destinatario = Stub.getUtenteStub();
        given(utenteService.findById(requestDto.getIdDestinatario())).willReturn(destinatario);

        mvc.perform(MockMvcRequestBuilders.post("/messaggi/invia")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isBadRequest());
    }


    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failInviaMessaggioDestinatarioNotFound() throws Exception {
        InviaMessaggioDto requestDto = Stub.getInviaMessaggioDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        Utente user = Stub.getUtenteStub();
        given(utenteService.findFromJWT()).willReturn(user);

        given(utenteService.findById(requestDto.getIdDestinatario())).willReturn(null);

        mvc.perform(MockMvcRequestBuilders.post("/messaggi/invia")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isNotFound());
    }

}
