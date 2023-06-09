package com.ozan.exchange.mappers;

import com.ozan.exchange.models.Rate;
import com.ozan.exchange.models.response.RateResponse;

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
