package com.ozan.exchange.models;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Rate {

    private Boolean success;
    private Long timestamp;
    private String base;
    private String date;
    private Map<String,Double> rates;

}
