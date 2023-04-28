package it.polimi.padel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.padel.DTO.RequestLoginDto;
import it.polimi.padel.DTO.RequestSignupDto;
import it.polimi.padel.Stub;
import it.polimi.padel.exception.UserException;
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

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthControllerTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private UtenteService service;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void successLogin() throws Exception {
        RequestLoginDto requestDto = Stub.getRequestLoginDtoStub();
        String loginDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        given(service.login(any())).willReturn(Stub.getResponseLoginDtoStub());
        mvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginDTOAsJSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token", is("token")))
                .andExpect(jsonPath("$.ruolo", is("USER")))
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void failLogin() throws Exception {
        RequestLoginDto requestDto = Stub.getRequestLoginDtoStub();
        String loginDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        doThrow(new UserException("", HttpStatus.UNAUTHORIZED)).when(service).login(any());
        mvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginDTOAsJSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void successSignup() throws Exception {
        RequestSignupDto requestDto = Stub.getRequestSignupDtoStub();
        String signupDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        given(service.signup(any())).willReturn(Stub.getResponseSignupDtoStub());
        mvc.perform(MockMvcRequestBuilders.post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(signupDTOAsJSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("Beatrice")))
                .andExpect(jsonPath("$.cognome", is("Romeo")))
                .andExpect(jsonPath("$.email", is("beatrice.romeo@polimi.it")));
    }

    @Test
    public void failSignupEmailUsata() throws Exception {
        RequestSignupDto requestDto = Stub.getRequestSignupDtoStub();
        String signupDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        doThrow(new UserException("Email già in uso", HttpStatus.BAD_REQUEST)).when(service).signup(any());
        mvc.perform(MockMvcRequestBuilders.post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(signupDTOAsJSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void failSignupEmailNonValida() throws Exception {
        RequestSignupDto requestDto = Stub.getRequestSignupDtoStub();
        requestDto.setEmail("emailnonvalida");
        String signupDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        doThrow(new UserException("Email non valida", HttpStatus.BAD_REQUEST)).when(service).signup(any());
        mvc.perform(MockMvcRequestBuilders.post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(signupDTOAsJSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"USER"})
    public void successIsValidUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/auth/isValidUser")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(true)));
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failIsValidUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/auth/isValidUser")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void successIsValidAdmin() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/auth/isValidAdmin")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(true)));
    }

    @Test
    @WithMockUser(value = "spring", roles = {"USER"})
    public void failIsValidAdmin() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/auth/isValidAdmin")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }
}
