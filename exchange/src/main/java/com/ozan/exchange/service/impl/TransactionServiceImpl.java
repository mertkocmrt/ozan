package com.ozan.exchange.service.impl;


import com.ozan.exchange.enumation.ErrorEnum;
import com.ozan.exchange.exception.ExchangeException;
import com.ozan.exchange.model.entity.Transaction;
import com.ozan.exchange.model.response.TransactionResponse;
import com.ozan.exchange.repository.TransactionRepository;
import com.ozan.exchange.service.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.ozan.exchange.specification.TransactionSpecification.transactionDateEquals;
import static com.ozan.exchange.specification.TransactionSpecification.transactionIdEquals;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactions(Long transactionId, String transactionDate, Pageable page) {

        if(Objects.isNull(transactionId) && Objects.isNull(transactionDate)) {
            throw new ExchangeException(ErrorEnum.ALL_INPUTS_ARE_EMPTY_ERROR.name());
        }

        Page<Transaction> transactions = transactionRepository.findAll(where(transactionIdEquals(transactionId)).and(transactionDateEquals(transactionDate)), page);

        if(!transactions.isEmpty()){
            return transactions.get().toList();
        }

        return null;
    }
}
