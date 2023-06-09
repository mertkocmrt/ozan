package com.ozan.exchange.controllers;

import com.ozan.exchange.models.request.RateRequest;
import com.ozan.exchange.models.response.RateResponse;
import com.ozan.exchange.services.RateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/exchange/rate")
public class RateController {

    private final RateService rateService;

    public RateController(RateService rateService){
        this.rateService = rateService;
    }

    @GetMapping
    public ResponseEntity<RateResponse> getRate(@RequestBody RateRequest request){
        return ResponseEntity.ok(rateService.getRate(request.getSourceCurrency(), request.getTargetCurrency()));
    }
}
