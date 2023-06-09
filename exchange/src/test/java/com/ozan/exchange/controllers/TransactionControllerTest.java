package com.ozan.exchange.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ozan.exchange.ExchangeApplication;
import com.ozan.exchange.models.entity.Transaction;
import com.ozan.exchange.models.request.ConversionRequest;
import com.ozan.exchange.models.request.RateRequest;
import com.ozan.exchange.models.request.TransactionRequest;
import com.ozan.exchange.repositories.TransactionRepository;
import org.junit.Before;
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
public class TransactionControllerTest {
    @Autowired
    private MockMvc mvc;

    private Transaction transaction;

    @Autowired
    private TransactionRepository transactionRepository;

    @Before
    public void init() {
        transaction = new Transaction();
        transaction.setTransactionId(1L);
        transaction.setTransactionDate("2023-06-09");
        transactionRepository.save(transaction);
    }

    @Test
    public void getTransactions_postRequest_thenStatus400()
            throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/exchange/transaction")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void convert_noData_thenStatus500()
            throws Exception {
        String request = new ObjectMapper()
                .writeValueAsString(new TransactionRequest(10L,"50"));
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/exchange/transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void convert_withRequestBody_thenStatus200()
            throws Exception {
        String request = new ObjectMapper()
                .writeValueAsString(new TransactionRequest(1L,"2023-06-09"));
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/exchange/transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().is2xxSuccessful());
    }
}
