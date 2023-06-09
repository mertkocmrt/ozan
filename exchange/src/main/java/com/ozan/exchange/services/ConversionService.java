package com.ozan.exchange.services;

import com.ozan.exchange.models.response.ConversionResponse;

import java.math.BigDecimal;

public interface ConversionService {

    ConversionResponse convert(BigDecimal amount, String sourceCurrency, String targetCurrency, String transactionDate);
}
