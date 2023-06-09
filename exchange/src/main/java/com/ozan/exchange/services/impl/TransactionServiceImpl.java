package com.ozan.exchange.services.impl;


import com.ozan.exchange.enumations.ErrorEnum;
import com.ozan.exchange.exceptions.ExchangeException;
import com.ozan.exchange.mappers.TransactionConverter;
import com.ozan.exchange.models.entity.Transaction;
import com.ozan.exchange.models.response.TransactionResponse;
import com.ozan.exchange.repositories.TransactionRepository;
import com.ozan.exchange.services.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.ozan.exchange.specifications.TransactionSpecification.*;
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
    public List<TransactionResponse> getTransactions(Long transactionId, String transactionDate, Pageable page) {

        if(Objects.isNull(transactionId) && Objects.isNull(transactionDate)) {
            throw new ExchangeException(ErrorEnum.ALL_INPUTS_ARE_EMPTY_ERROR.name());
        }

        Page<Transaction> transactions = transactionRepository
                .findAll(where(findTransactionsBuilder(transactionId,transactionDate)), page);

        if(!transactions.isEmpty()){
            TransactionConverter transactionConverter = new TransactionConverter();
            return transactionConverter.entityToResponse(transactions.get().toList());
        } else {
            throw new ExchangeException(ErrorEnum.TRANSACTION_NOT_FOUND.name());
        }
    }
}
