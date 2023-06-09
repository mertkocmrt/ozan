package com.ozan.exchange.services;

import com.ozan.exchange.exceptions.ExchangeException;
import com.ozan.exchange.models.entity.Transaction;
import com.ozan.exchange.models.response.TransactionResponse;
import com.ozan.exchange.repositories.TransactionRepository;
import com.ozan.exchange.services.impl.TransactionServiceImpl;
import com.ozan.exchange.specifications.TransactionSpecification;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    private Transaction transaction;

    private TransactionSpecification spec = new TransactionSpecification();

    @Before
    public void init() {
        transaction = new Transaction();
        transaction.setTransactionId(1L);
        transaction.setTransactionDate("2023-06-09");
        transactionRepository.save(transaction);
    }

    @Test
    public void saveTransaction() {
        Transaction transaction = new Transaction(1L,null,null,null,null,null);
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);
        Transaction response = transactionService.saveTransaction(new Transaction(null,null,null,null,null,null));

        assertThat(1L).isEqualTo(response.getTransactionId());
    }

    @Test(expected = ExchangeException.class)
    public void getTransactions_nullInputs() {
        transactionService.getTransactions(null, null, null);
    }

    @Test(expected = ExchangeException.class)
    public void getTransactions_dataNotFound() {
        List<Transaction> expected = new ArrayList<>();
        Page expectedPage = new PageImpl(expected);
        when(transactionRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(expectedPage);
        transactionService.getTransactions(1L, "2023-06-09", expectedPage.getPageable());
    }

    @Test
    public void getTransactions() {
        List<Transaction> expected = new ArrayList<>();
        expected.add(transaction);
        Page expectedPage = new PageImpl(expected);
        when(transactionRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(expectedPage);
        List<TransactionResponse> responseList = transactionService.getTransactions(1L, "2023-06-09", expectedPage.getPageable());
        assertThat(responseList.size()).isEqualTo(1);
    }
}
