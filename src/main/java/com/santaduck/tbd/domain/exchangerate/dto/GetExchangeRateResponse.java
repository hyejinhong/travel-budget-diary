package com.santaduck.tbd.domain.exchangerate.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class GetExchangeRateResponse {
    private String currencyCode;
    private Double exchangeRate;
}
