package com.ozan.exchange.configuration;

import com.ozan.exchange.enumation.ErrorEnum;
import com.ozan.exchange.exception.ExchangeException;
import com.ozan.exchange.model.response.ErrorResponse;
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
