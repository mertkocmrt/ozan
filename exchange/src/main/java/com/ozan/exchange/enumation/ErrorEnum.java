package com.ozan.exchange.enumation;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum ErrorEnum {
    CONSUMING_RATE_API_ERROR("10100", "Error when consuming rates");

    private final String code;
    private final String message;

    ErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
