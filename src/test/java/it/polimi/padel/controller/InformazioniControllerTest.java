package it.polimi.padel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.padel.DTO.RequestNewsDto;
import it.polimi.padel.DTO.RequestRegoleDto;
import it.polimi.padel.Stub;
import it.polimi.padel.exception.GenericException;
import it.polimi.padel.service.InformazioniService;
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
public class InformazioniControllerTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private InformazioniService informazioniService;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void successGetInformazioni() throws Exception {
        given(informazioniService.getInformazioni()).willReturn(List.of(Stub.getInformazioniStub()));

        mvc.perform(MockMvcRequestBuilders.get("/informazioni")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void successGetNews() throws Exception {
        given(informazioniService.getNews()).willReturn(List.of(Stub.getInfoVarieStub()));

        mvc.perform(MockMvcRequestBuilders.get("/informazioni/getNews")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failGetNews() throws Exception {
        doThrow(new GenericException(HttpStatus.INTERNAL_SERVER_ERROR, "")).when(informazioniService).getNews();
        mvc.perform(MockMvcRequestBuilders.get("/informazioni/getNews")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void successGetInfoVarie() throws Exception {
        given(informazioniService.getInfoVarie()).willReturn(List.of(Stub.getInfoVarieStub()));

        mvc.perform(MockMvcRequestBuilders.get("/informazioni/getInfoVarie")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failGetInfoVarie() throws Exception {
        doThrow(new GenericException(HttpStatus.INTERNAL_SERVER_ERROR, "")).when(informazioniService).getInfoVarie();
        mvc.perform(MockMvcRequestBuilders.get("/informazioni/getInfoVarie")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void successGetContatti() throws Exception {
        given(informazioniService.getContatti()).willReturn(List.of(Stub.getContattoStub()));

        mvc.perform(MockMvcRequestBuilders.get("/informazioni/getContatti")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failGetContatti() throws Exception {
        doThrow(new GenericException(HttpStatus.INTERNAL_SERVER_ERROR, "")).when(informazioniService).getContatti();
        mvc.perform(MockMvcRequestBuilders.get("/informazioni/getContatti")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void successGetOrari() throws Exception {
        given(informazioniService.getOrariApertura()).willReturn(List.of(Stub.getOrarioStrutturaStub()));

        mvc.perform(MockMvcRequestBuilders.get("/informazioni/getOrari")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failGetOrari() throws Exception {
        doThrow(new GenericException(HttpStatus.INTERNAL_SERVER_ERROR, "")).when(informazioniService).getOrariApertura();
        mvc.perform(MockMvcRequestBuilders.get("/informazioni/getOrari")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void successGetRegole() throws Exception {
        given(informazioniService.getRegole()).willReturn(List.of(Stub.getRegolaStub()));

        mvc.perform(MockMvcRequestBuilders.get("/informazioni/getRegole")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failGetRegole() throws Exception {
        doThrow(new GenericException(HttpStatus.INTERNAL_SERVER_ERROR, "")).when(informazioniService).getRegole();
        mvc.perform(MockMvcRequestBuilders.get("/informazioni/getRegole")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void successUpdateRegole() throws Exception {
        RequestRegoleDto requestDto = Stub.getRequestRegoleDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);
        given(informazioniService.updateRegole(any())).willReturn(List.of(Stub.getRegolaStub()));
        mvc.perform(MockMvcRequestBuilders.put("/informazioni/regole")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failUpdateRegole() throws Exception {
        RequestRegoleDto requestDto = Stub.getRequestRegoleDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);
        doThrow(new GenericException(HttpStatus.INTERNAL_SERVER_ERROR, "")).when(informazioniService).updateRegole(any());
        mvc.perform(MockMvcRequestBuilders.put("/informazioni/regole")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failUpdateOrari() throws Exception {
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString("");
        doThrow(new GenericException(HttpStatus.INTERNAL_SERVER_ERROR, "")).when(informazioniService).updateOrari(any());
        mvc.perform(MockMvcRequestBuilders.put("/informazioni/orari")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void successUpdateNews() throws Exception {
        RequestNewsDto requestDto = Stub.getRequestNewsDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);
        given(informazioniService.updateNews(any())).willReturn(List.of(Stub.getInfoVarieStub()));
        mvc.perform(MockMvcRequestBuilders.put("/informazioni/news")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failUpdateNews() throws Exception {
        RequestNewsDto requestDto = Stub.getRequestNewsDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);
        doThrow(new GenericException(HttpStatus.INTERNAL_SERVER_ERROR, "")).when(informazioniService).updateNews(any());
        mvc.perform(MockMvcRequestBuilders.put("/informazioni/news")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isInternalServerError());
    }
}
