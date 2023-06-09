package com.ozan.exchange.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "TRANSACTIONS")
public class Transaction {

    @Id
    @GeneratedValue
    private Long transactionId;
    private String transactionDate;
    private String sourceCurrency;
    private String targetCurrency;
    private BigDecimal sourceAmount;
    private BigDecimal targetAmount;
}
