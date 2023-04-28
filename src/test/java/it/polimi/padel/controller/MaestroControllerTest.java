package it.polimi.padel.controller;

import it.polimi.padel.Stub;
import it.polimi.padel.service.MaestroService;
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
public class MaestroControllerTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private MaestroService maestroService;
    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void successGetMaestri() throws Exception {
        given(maestroService.getMaestri()).willReturn(List.of(Stub.getResponseMaestroDtoStub()));

        mvc.perform(MockMvcRequestBuilders.get("/maestri")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
