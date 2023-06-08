package com.ozan.exchange.controller;

import com.ozan.exchange.model.Rate;
import com.ozan.exchange.model.request.RateRequest;
import com.ozan.exchange.model.response.RateResponse;
import com.ozan.exchange.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/exchange/rate")
public class RateController {

    private final RateService rateService;

    @Autowired
    public RateController(RateService rateService){
        this.rateService = rateService;
    }

    @GetMapping
    public ResponseEntity<RateResponse> getRate(@RequestBody RateRequest request){
        return ResponseEntity.ok(rateService.getRate(request.getSourceCurrency(), request.getTargetCurrency()));
    }
}
