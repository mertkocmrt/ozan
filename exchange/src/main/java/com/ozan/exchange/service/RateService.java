package com.ozan.exchange.service;

import com.ozan.exchange.model.Rate;
import com.ozan.exchange.model.request.RateRequest;
import com.ozan.exchange.model.response.RateResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface RateService {

    RateResponse getRate(String baseCurrency, String targetCurrency);

}
