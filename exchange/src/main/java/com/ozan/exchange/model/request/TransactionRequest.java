package com.ozan.exchange.model.request;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TransactionRequest extends BaseRequest implements Serializable {
    private Long transactionId;
    private String transactionDate;
}
