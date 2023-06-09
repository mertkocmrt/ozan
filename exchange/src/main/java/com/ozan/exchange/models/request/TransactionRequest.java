package com.ozan.exchange.models.request;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class TransactionRequest extends BaseRequest implements Serializable {
    private Long transactionId;
    private String transactionDate;
}
