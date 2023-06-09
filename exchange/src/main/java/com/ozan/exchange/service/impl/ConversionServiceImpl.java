package com.ozan.exchange.service.impl;

import com.ozan.exchange.model.entity.Transaction;
import com.ozan.exchange.model.response.ConversionResponse;
import com.ozan.exchange.model.response.RateResponse;
import com.ozan.exchange.service.ConversionService;
import com.ozan.exchange.service.RateService;
import com.ozan.exchange.service.TransactionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ConversionServiceImpl implements ConversionService {

    private final RateService rateService;
    private final TransactionService transactionService;

    public ConversionServiceImpl(RateService rateService, TransactionService transactionService){
        this.rateService = rateService;
        this.transactionService = transactionService;
    }

    @Override
    public ConversionResponse convert(BigDecimal amount, String sourceCurrency, String targetCurrency, String transactionDate){
        RateResponse rateResponse = rateService.getRate(sourceCurrency, targetCurrency);

        BigDecimal targetAmount = calculateAmount(amount, rateResponse.getExchangeRate().stream().findFirst().get());

        Transaction transaction = transactionService.saveTransaction(Transaction
                        .builder()
                        .sourceAmount(amount)
                        .targetAmount(targetAmount)
                        .sourceCurrency(sourceCurrency)
                        .targetCurrency(targetCurrency)
                        .transactionDate(transactionDate)
                        .build());

        ConversionResponse conversionResponse = new ConversionResponse();
        conversionResponse.setTargetAmount(targetAmount);
        conversionResponse.setTransactionId(transaction.getTransactionId());
        return conversionResponse;
    }

    private BigDecimal calculateAmount(BigDecimal buyAmount, Double rate){
        return buyAmount.multiply(BigDecimal.valueOf(rate));
    }

}
