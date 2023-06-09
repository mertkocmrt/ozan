package com.ozan.exchange.services;

import com.ozan.exchange.models.entity.Transaction;
import com.ozan.exchange.models.response.TransactionResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionService {

    Transaction saveTransaction(Transaction transaction);
    List<TransactionResponse> getTransactions(Long transactionId, String transactionDate, Pageable page);
}
