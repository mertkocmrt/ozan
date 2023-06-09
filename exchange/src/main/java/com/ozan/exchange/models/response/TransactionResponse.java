package com.ozan.exchange.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {

    private Long transactionId;
    private String transactionDate;
    private String sourceCurrency;
    private String targetCurrency;
    private BigDecimal targetAmount;

}
