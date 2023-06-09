package com.ozan.exchange.enumations;

import lombok.Getter;

@Getter
public enum ErrorEnum {
    CONSUMING_RATE_API_ERROR("10100", "Error when consuming rates"),
    ALL_INPUTS_ARE_EMPTY_ERROR("10200", "All inputs cannot be empty"),
    TRANSACTION_NOT_FOUND("10300", "No transaction is found"),
    SOURCE_AMOUNT_IS_EMPTY("10400", "Source amount cannot be empty");
    private final String code;
    private final String message;

    ErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
