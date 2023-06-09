package com.ozan.exchange.specifications;

import com.ozan.exchange.models.entity.Transaction;
import com.ozan.exchange.repositories.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static com.ozan.exchange.specifications.TransactionSpecification.*;
import static org.junit.Assert.assertTrue;
import static org.springframework.data.jpa.domain.Specification.where;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@SpringBootTest
public class TransactionSpecificationTest {

    @Autowired
    private TransactionRepository transactionRepository;

    private Transaction transaction;

    @Before
    public void init() {
        transaction = new Transaction();
        transaction.setTransactionId(1L);
        transaction.setTransactionDate("2023-06-09");
        transactionRepository.save(transaction);
    }

    @Test
    public void transactionIdEquals_givenId_thenCorrect() {
        Transaction transaction = new Transaction(1L,"2023-06-09",null,null,null,null);

        Page<Transaction> results = transactionRepository.findAll(where(transactionIdEquals(transaction.getTransactionId())), Pageable.unpaged());

        assertTrue(results
                .stream()
                .filter(t->t.getTransactionId().equals(transaction.getTransactionId())).findAny().isPresent());
    }

    @Test
    public void transactionDateEquals_givenDate_thenCorrect() {
        Transaction transaction = new Transaction(1L,"2023-06-09",null,null,null,null);

        Page<Transaction> results = transactionRepository.findAll(where(transactionDateEquals(transaction.getTransactionDate())), Pageable.unpaged());

        assertTrue(results
                .stream()
                .filter(t->t.getTransactionDate().equals(transaction.getTransactionDate())).findAny().isPresent());
    }

    @Test
    public void findTransactionsBuilder_givenDate_thenCorrect() {
        Transaction transaction = new Transaction(null,"2023-06-09",null,null,null,null);

        Page<Transaction> results = transactionRepository.findAll(where(findTransactionsBuilder(transaction.getTransactionId(),transaction.getTransactionDate())), Pageable.unpaged());

        assertTrue(results
                .stream()
                .filter(t->t.getTransactionDate().equals(transaction.getTransactionDate())).findAny().isPresent());
    }

    @Test
    public void findTransactionsBuilder_givenId_thenCorrect() {
        Transaction transaction = new Transaction(1L,null,null,null,null,null);

        Page<Transaction> results = transactionRepository.findAll(where(findTransactionsBuilder(transaction.getTransactionId(),transaction.getTransactionDate())), Pageable.unpaged());

        assertTrue(results
                .stream()
                .filter(t->t.getTransactionId().equals(transaction.getTransactionId())).findAny().isPresent());
    }

    @Test
    public void findTransactionsBuilder_givenIdAndGivenDate_thenCorrect() {
        Transaction transaction = new Transaction(1L,"2023-06-09",null,null,null,null);

        Page<Transaction> results = transactionRepository.findAll(where(findTransactionsBuilder(transaction.getTransactionId(),transaction.getTransactionDate())), Pageable.unpaged());

        assertTrue(results
                .stream()
                .filter(t->t.getTransactionDate().equals(transaction.getTransactionDate()))
                .filter(t->t.getTransactionId().equals(transaction.getTransactionId())).findAny().isPresent());
    }
}
