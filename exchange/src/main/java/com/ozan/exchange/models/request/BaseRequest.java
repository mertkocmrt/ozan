package com.ozan.exchange.models.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseRequest {
    @Builder.Default
    private int page=0;
    @Builder.Default
    private int size=5;
}
