package com.ozan.exchange.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorResponse {

    private String responseMessage;
    private String responseCode;
}
