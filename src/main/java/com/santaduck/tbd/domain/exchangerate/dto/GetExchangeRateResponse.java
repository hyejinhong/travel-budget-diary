package com.santaduck.tbd.domain.exchangerate.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter @Setter
public class GetExchangeRateResponse {
    private String currencyCode;
    private String currencyName;
    private Double exchangeRate;
    private LocalDateTime modifiedAt;
}
