package com.ozan.exchange.services.impl;

import com.ozan.exchange.enumations.ErrorEnum;
import com.ozan.exchange.exceptions.ExchangeException;
import com.ozan.exchange.models.entity.Transaction;
import com.ozan.exchange.models.response.ConversionResponse;
import com.ozan.exchange.models.response.RateResponse;
import com.ozan.exchange.services.ConversionService;
import com.ozan.exchange.services.RateService;
import com.ozan.exchange.services.TransactionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

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
        if(Objects.isNull(buyAmount)){
            throw new ExchangeException(ErrorEnum.SOURCE_AMOUNT_IS_EMPTY.name());
        }
        return buyAmount.multiply(BigDecimal.valueOf(rate));
    }

}
