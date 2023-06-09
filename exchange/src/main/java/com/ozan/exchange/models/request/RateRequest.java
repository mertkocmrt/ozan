package com.ozan.exchange.models.request;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class RateRequest implements Serializable {
    private String sourceCurrency;
    private String targetCurrency;
}
