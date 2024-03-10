package com.santaduck.tbd.domain.exchangerate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FetchedExchangeRateInfo {
    private List<FetchedExchangeRateVo> exchangeRates;
}
