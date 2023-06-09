package com.ozan.exchange.services;

import com.ozan.exchange.exceptions.ExchangeException;
import com.ozan.exchange.models.Rate;
import com.ozan.exchange.models.entity.Transaction;
import com.ozan.exchange.models.response.ConversionResponse;
import com.ozan.exchange.models.response.RateResponse;
import com.ozan.exchange.repositories.TransactionRepository;
import com.ozan.exchange.services.impl.ConversionServiceImpl;
import com.ozan.exchange.services.impl.RateServiceImpl;
import com.ozan.exchange.services.impl.TransactionServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class ConversionServiceTest {

    @InjectMocks
    private ConversionServiceImpl conversionService;

    @Mock
    private RateServiceImpl rateService;
    @Mock
    private TransactionServiceImpl transactionService;

    @Test
    public void convert() {
        RateResponse rateResponse = new RateResponse(List.of(1.5));
        Transaction transaction = new Transaction(1L,"2023-06-09",null,null,null,null);
        ConversionResponse conversionResponse = new ConversionResponse(BigDecimal.ONE, 1L);
        when(rateService.getRate(any(String.class),any(String.class))).thenReturn(rateResponse);
        when(transactionService.saveTransaction(any(Transaction.class))).thenReturn(transaction);
        ConversionResponse actual = conversionService.convert(BigDecimal.valueOf(1.0),"EUR","USD","2023-06-09");

        assertThat(actual.getTransactionId()).isEqualTo(conversionResponse.getTransactionId());
        verify(transactionService, times(1)).saveTransaction(any(Transaction.class));
    }

    @Test(expected = NullPointerException.class)
    public void convert_emptyTransaction() {
        RateResponse rateResponse = new RateResponse(List.of(1.5));
        when(rateService.getRate(any(String.class),any(String.class))).thenReturn(rateResponse);
        when(transactionService.saveTransaction(any(Transaction.class))).thenReturn(null);
        conversionService.convert(BigDecimal.valueOf(1.0),"EUR","USD","2023-06-09");
    }

}
