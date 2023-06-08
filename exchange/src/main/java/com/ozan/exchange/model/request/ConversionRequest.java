package com.ozan.exchange.model.request;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ConversionRequest implements Serializable {
    private BigDecimal amount;
    private String sourceCurrency;
    private String targetCurrency;
    @Builder.Default
    private String transactionDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
}
