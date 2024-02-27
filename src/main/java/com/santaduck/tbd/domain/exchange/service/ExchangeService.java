package com.santaduck.tbd.domain.exchange.service;

import com.santaduck.tbd.domain.exchange.dto.ExchangeRate;
import com.santaduck.tbd.domain.exchange.dto.GetExchangeRateResponse;
import com.santaduck.tbd.domain.exchange.util.ExchangeRateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final ExchangeRateUtil exchangeRateUtil;

    public GetExchangeRateResponse getCurrentCurrency(String code) throws Exception {
        exchangeRateUtil.fetchExchangeRates();
        return null;
    }
}
