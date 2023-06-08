package com.ozan.exchange.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Rate {

    private Boolean success;
    private Long timestamp;
    private String base;
    private String date;
    private Map<String,Double> rates;

}
