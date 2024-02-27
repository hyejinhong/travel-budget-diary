package com.santaduck.tbd.domain.exchange.controller;


import com.santaduck.tbd.domain.exchange.dto.GetExchangeRateResponse;
import com.santaduck.tbd.domain.exchange.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ExchangeController {

    private final ExchangeService currencyService;

    @GetMapping("/currency")
    public GetExchangeRateResponse getCurrentCurrency(@RequestParam String code) throws Exception {
        log.info("code : {}", code);
        currencyService.getCurrentCurrency(code);
        return null;
    }
}
