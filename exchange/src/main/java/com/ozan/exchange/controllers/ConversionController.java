package com.ozan.exchange.controllers;

import com.ozan.exchange.models.request.ConversionRequest;
import com.ozan.exchange.models.response.ConversionResponse;
import com.ozan.exchange.services.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/exchange/conversion")
public class ConversionController {

    private final ConversionService conversionService;

    public ConversionController(ConversionService conversionService){
        this.conversionService = conversionService;
    }

    @PostMapping
    @RequestMapping("convert")
    public ResponseEntity<ConversionResponse> convert(@RequestBody ConversionRequest request){
        return ResponseEntity.ok(conversionService.convert(request.getAmount(),
                request.getSourceCurrency(),
                request.getTargetCurrency(),
                request.getTransactionDate()));
    }
}
