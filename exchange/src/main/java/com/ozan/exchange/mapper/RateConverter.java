package com.ozan.exchange.mapper;

import com.ozan.exchange.model.Rate;
import com.ozan.exchange.model.response.RateResponse;

public class RateConverter {

    public RateConverter(){}

    public RateResponse entityToResponse(Rate rate){
        RateResponse rateResponse = new RateResponse();
        if(!rate.getRates().isEmpty()) {
            rateResponse.setExchangeRate(rate.getRates().values().stream().toList());
        }

        return rateResponse;
    }

}
