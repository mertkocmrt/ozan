package com.ozan.exchange.services.impl;

import com.ozan.exchange.enumations.ErrorEnum;
import com.ozan.exchange.exceptions.ExchangeException;
import com.ozan.exchange.mappers.RateConverter;
import com.ozan.exchange.models.Rate;
import com.ozan.exchange.models.response.RateResponse;
import com.ozan.exchange.services.RateService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class RateServiceImpl implements RateService {

    @Value("${fixer.url}")
    private String fixerUrl;
    @Value("${fixer.access.key}")
    private String fixerAccessKey;

    private final RestTemplate restTemplate;

    public RateServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public RateResponse getRate(String baseCurrency, String targetCurrency) {
        Rate rate = consumeRate(baseCurrency, targetCurrency);

        if (rate != null && rate.getSuccess()) {
            RateConverter rateConverter = new RateConverter();
            return rateConverter.entityToResponse(rate);
        } else {
            throw new ExchangeException(ErrorEnum.CONSUMING_RATE_API_ERROR.name());
        }
    }

    public Rate consumeRate(String baseCurrency, String targetCurrency) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        Map<String, String> map = new HashMap<>();
        map.put("access_key", fixerAccessKey);
        map.put("base", baseCurrency);
        map.put("symbols", targetCurrency);
        return restTemplate.exchange(fixerUrl, HttpMethod.POST, entity, Rate.class, map).getBody();
    }
}
