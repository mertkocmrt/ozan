package com.ozan.exchange.model.response;

import com.ozan.exchange.model.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private List<Transaction> transactionList;

}
