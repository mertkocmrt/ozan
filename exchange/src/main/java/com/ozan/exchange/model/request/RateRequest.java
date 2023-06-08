package com.ozan.exchange.model.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class RateRequest implements Serializable {
    private String sourceCurrency;
    private String targetCurrency;
}
