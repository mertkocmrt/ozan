package com.ozan.exchange.service.impl;

import com.ozan.exchange.enumation.ErrorEnum;
import com.ozan.exchange.exception.ExchangeException;
import com.ozan.exchange.mapper.ConversionConverter;
import com.ozan.exchange.mapper.RateConverter;
import com.ozan.exchange.model.Rate;
import com.ozan.exchange.model.request.ConversionRequest;
import com.ozan.exchange.model.response.ConversionResponse;
import com.ozan.exchange.model.response.RateResponse;
import com.ozan.exchange.service.ConversionService;
import com.ozan.exchange.service.RateService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ConversionServiceImpl implements ConversionService {

    private final RateService rateService;

    public ConversionServiceImpl(RateService rateService){
        this.rateService = rateService;
    }

//    @Override
//    public ConversionResponse convert(BigDecimal amount, String sourceCurrency, String targetCurrency, String transactionDate){
//        RateResponse rateResponse = rateService.getRate(sourceCurrency, targetCurrency);
//        RateConverter rateConverter = new RateConverter();
//        Rate rate = rateConverter.responseToEntity(rateResponse);
//
//        BigDecimal targetAmount = calculateAmount(amount, rate.getRates().get(targetCurrency));
//
//        ConversionConverter conversionConverter = new ConversionConverter();
//        return conversionConverter.entityToResponse(rate);
//    }

//    private BigDecimal calculateAmount(BigDecimal buyAmount, Double rate){
//        return buyAmount.multiply(BigDecimal.valueOf(rate));
//    }

}
