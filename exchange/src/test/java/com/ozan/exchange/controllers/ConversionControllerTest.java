package com.ozan.exchange.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ozan.exchange.ExchangeApplication;
import com.ozan.exchange.models.request.ConversionRequest;
import com.ozan.exchange.models.request.RateRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ExchangeApplication.class)
@AutoConfigureMockMvc
public class ConversionControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void convert_emptyBody_thenStatus400()
            throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/exchange/conversion/convert")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void convert_withEmptyAmount_thenStatus500()
            throws Exception {
        String request = new ObjectMapper()
                .writeValueAsString(new RateRequest("EUR","USD"));
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/exchange/conversion/convert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void convert_withRequestBody_thenStatus200()
            throws Exception {
        String request = new ObjectMapper()
                .writeValueAsString(new ConversionRequest(BigDecimal.valueOf(100.0),"EUR","USD",null));
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/exchange/conversion/convert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().is2xxSuccessful());
    }

}
