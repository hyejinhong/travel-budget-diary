package com.santaduck.tbd.domain.exchangerate.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class GetAllExchangeRateResponse {
    private int size;
    private List<GetExchangeRateResponse> exchangeRates;
}
