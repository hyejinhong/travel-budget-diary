package com.santaduck.tbd.domain.exchangerate.controller;

import com.santaduck.tbd.domain.exchangerate.dto.GetExchangeRateResponse;
import com.santaduck.tbd.domain.exchangerate.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    @GetMapping("/exchange-rate")
    public GetExchangeRateResponse getCurrentExchangeRate(@RequestParam String code) throws Exception {
        log.info("code : {}", code);
        return exchangeRateService.getCurrentExchangeRate(code);
    }
}
