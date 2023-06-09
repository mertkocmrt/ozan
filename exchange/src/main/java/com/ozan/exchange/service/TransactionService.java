package com.ozan.exchange.service;

import com.ozan.exchange.model.entity.Transaction;
import com.ozan.exchange.model.response.TransactionResponse;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface TransactionService {

    Transaction saveTransaction(Transaction transaction);
    List<TransactionResponse> getTransactions(Long transactionId, String transactionDate, Pageable page);
}
