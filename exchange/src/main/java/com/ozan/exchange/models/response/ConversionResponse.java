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
public class ConversionResponse{

    private BigDecimal targetAmount;
    private Long transactionId;
}
