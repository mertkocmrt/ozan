package com.ozan.exchange.controller;

import com.ozan.exchange.model.request.ConversionRequest;
import com.ozan.exchange.model.response.ConversionResponse;
import com.ozan.exchange.service.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/exchange/conversion")
public class ConversionController {

    private final ConversionService conversionService;

    @Autowired
    public ConversionController(ConversionService conversionService){
        this.conversionService = conversionService;
    }

//    @PostMapping
//    @RequestMapping("convert")
//    public ResponseEntity<ConversionResponse> convert(@RequestBody ConversionRequest request){
//        return ResponseEntity.ok(conversionService.convert(request.getAmount(),
//                request.getSourceCurrency(),
//                request.getTargetCurrency(),
//                request.getTransactionDate()));
//    }
}
