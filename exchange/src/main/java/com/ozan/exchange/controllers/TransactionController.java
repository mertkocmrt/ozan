package com.ozan.exchange.controllers;

import com.ozan.exchange.models.request.TransactionRequest;
import com.ozan.exchange.models.response.TransactionResponse;
import com.ozan.exchange.services.TransactionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exchange/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getTransactions(@RequestBody TransactionRequest request){
        return ResponseEntity.ok(transactionService.getTransactions(request.getTransactionId(), request.getTransactionDate(),
                PageRequest.of(request.getPage(), request.getSize())));
    }

}
