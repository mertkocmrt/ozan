package com.ozan.exchange.specification;

import com.ozan.exchange.model.entity.Transaction;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class TransactionSpecification {

    public static Specification<Transaction> transactionIdEquals(Long transactionId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("transactionId"), transactionId);
    }

    public static Specification<Transaction> transactionDateEquals(Date transactionDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("transactionDate"), transactionDate);
    }

}
