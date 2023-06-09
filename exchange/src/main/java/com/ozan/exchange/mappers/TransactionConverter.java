package com.ozan.exchange.mappers;

import com.ozan.exchange.models.entity.Transaction;
import com.ozan.exchange.models.response.TransactionResponse;

import java.util.ArrayList;
import java.util.List;

public class TransactionConverter {

    public TransactionResponse entityToResponse(Transaction transaction) {
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setTransactionId(transaction.getTransactionId());
        transactionResponse.setTransactionDate(transaction.getTransactionDate());
        transactionResponse.setSourceCurrency(transaction.getSourceCurrency());
        transactionResponse.setTargetCurrency(transaction.getTargetCurrency());
        transactionResponse.setTargetAmount(transaction.getTargetAmount());
        return transactionResponse;
    }
    public List<TransactionResponse> entityToResponse(List<Transaction> transactionList) {
        List<TransactionResponse> transactionResponseList = new ArrayList<>();

        for(Transaction transaction : transactionList){
            transactionResponseList.add(entityToResponse(transaction));
        }
        return transactionResponseList;
    }

}
