package com.ozan.exchange.specifications;

import com.ozan.exchange.models.entity.Transaction;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class TransactionSpecification {

    public static Specification<Transaction> transactionIdEquals(Long transactionId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("transactionId"), transactionId);
    }

    public static Specification<Transaction> transactionDateEquals(String transactionDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("transactionDate"), transactionDate);
    }

    public static Specification<Transaction> findTransactionsBuilder(Long transactionId, String transactionDate) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<Predicate>();
            if (transactionId != null) {
                list.add(transactionIdEquals(transactionId).toPredicate(root, query, criteriaBuilder));
            }
            if (transactionDate != null) {
                list.add(transactionDateEquals(transactionDate).toPredicate(root, query, criteriaBuilder));
            }
            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        };

    }

}
