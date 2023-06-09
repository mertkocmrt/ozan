package com.ozan.exchange.services;

import com.ozan.exchange.models.response.RateResponse;

public interface RateService {

    RateResponse getRate(String baseCurrency, String targetCurrency);

}
