package it.polimi.padel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.padel.DTO.RequestCampoDto;
import it.polimi.padel.Stub;
import it.polimi.padel.exception.CampoNotFoundException;
import it.polimi.padel.service.CampoService;
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
public class CampoControllerTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private CampoService campoService;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }
    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void successAggiungiCampo() throws Exception {
        RequestCampoDto requestDto = Stub.getRequestCampoDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        given(campoService.aggiungiCampo(any())).willReturn(Stub.getResponseCampoDtoStub());
        mvc.perform(MockMvcRequestBuilders.post("/campi")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("Campo 1")))
                .andExpect(jsonPath("$.urlImmagine", is("url immagine")))
                .andExpect(jsonPath("$.id", is(1)));

    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void successRimuoviCampo() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/campi/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failRimuoviCampoNotFound() throws Exception {
        doThrow(new CampoNotFoundException("Il campo non esiste", HttpStatus.NOT_FOUND)).when(campoService).deleteCampo(any());
        mvc.perform(MockMvcRequestBuilders.delete("/campi/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void successGetCampi() throws Exception {
        given(campoService.getCampi()).willReturn(List.of(Stub.getResponseCampoDtoStub()));
        mvc.perform(MockMvcRequestBuilders.get("/campi")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
