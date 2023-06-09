package com.ozan.exchange.configurations;

import com.ozan.exchange.enumations.ErrorEnum;
import com.ozan.exchange.exceptions.ExchangeException;
import com.ozan.exchange.models.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ExchangeException.class})
    protected ResponseEntity<ErrorResponse> handleExchangeException(ExchangeException e) {
        return ResponseEntity
                .internalServerError()
                .body(ErrorResponse
                        .builder()
                        .responseMessage(ErrorEnum.valueOf(e.getMessage()).getMessage())
                        .responseCode(ErrorEnum.valueOf(e.getMessage()).getCode())
                        .build());
    }

}
