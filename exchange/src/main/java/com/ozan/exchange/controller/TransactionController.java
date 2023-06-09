package com.ozan.exchange.controller;

import com.ozan.exchange.model.request.TransactionRequest;
import com.ozan.exchange.model.response.TransactionResponse;
import com.ozan.exchange.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exchange/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getTransactions(@RequestBody TransactionRequest request){
        return ResponseEntity.ok(transactionService.getTransactions(request.getTransactionId(), request.getTransactionDate(),
                PageRequest.of(request.getPage(), request.getSize())));
    }

}
