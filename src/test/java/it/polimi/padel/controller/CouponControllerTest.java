package it.polimi.padel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.padel.DTO.RequestGenerateCouponDto;
import it.polimi.padel.Stub;
import it.polimi.padel.exception.CouponException;
import it.polimi.padel.model.Coupon;
import it.polimi.padel.service.CouponService;
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
public class CouponControllerTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private CouponService couponService;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void successGetCoupons() throws Exception {
        given(couponService.getAll()).willReturn(List.of(Stub.getCouponStub()));
        mvc.perform(MockMvcRequestBuilders.get("/coupon")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].codice", is("codice")))
                .andExpect(jsonPath("$[0].valore", is(10.0)))
                .andExpect(jsonPath("$[0].tipo", is(Coupon.TipoCoupon.EURO.toString())));

    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void successCreateCoupon() throws Exception {
        RequestGenerateCouponDto requestDto = Stub.getRequestGenerateCouponDtoStub();
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);
        given(couponService.generateCoupon(any())).willReturn(Stub.getCouponStub());
        mvc.perform(MockMvcRequestBuilders.post("/coupon")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.codice", is("codice")))
                .andExpect(jsonPath("$.valore", is(10.0)))
                .andExpect(jsonPath("$.tipo", is(Coupon.TipoCoupon.EURO.toString())))
                .andExpect(jsonPath("$.utilizzato", is(false)));
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failCreateCouponInvalidType() throws Exception {
        RequestGenerateCouponDto requestDto = Stub.getRequestGenerateCouponDtoStub();
        requestDto.setTipo("invalid");
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        doThrow(new CouponException("Tipo di coupon non valido", HttpStatus.BAD_REQUEST)).when(couponService).generateCoupon(any());
        mvc.perform(MockMvcRequestBuilders.post("/coupon")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failCreateCouponInvalidValue() throws Exception {
        RequestGenerateCouponDto requestDto = Stub.getRequestGenerateCouponDtoStub();
        requestDto.setValore(-1.0f);
        String inputDTOAsJSON = new ObjectMapper().writeValueAsString(requestDto);

        doThrow(new CouponException("Valore del coupon non valido", HttpStatus.BAD_REQUEST)).when(couponService).generateCoupon(any());
        mvc.perform(MockMvcRequestBuilders.post("/coupon")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDTOAsJSON))
                .andExpect(status().isBadRequest());
    }


    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void successDeleteCoupon() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/coupon/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "spring", roles = {"ADMIN"})
    public void failDeleteCouponInvalidId() throws Exception {
        doThrow(new CouponException("Coupon non trovato", HttpStatus.NOT_FOUND)).when(couponService).deleteCoupon(any());
        mvc.perform(MockMvcRequestBuilders.delete("/coupon/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
