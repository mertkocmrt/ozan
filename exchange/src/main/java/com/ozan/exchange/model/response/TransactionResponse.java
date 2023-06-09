package com.ozan.exchange.model.response;

import com.ozan.exchange.model.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

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
