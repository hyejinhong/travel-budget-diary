package com.santaduck.tbd.domain.exchangerate.controller;

import com.santaduck.tbd.domain.exchangerate.dto.GetAllExchangeRateResponse;
import com.santaduck.tbd.domain.exchangerate.dto.GetExchangeRateResponse;
import com.santaduck.tbd.domain.exchangerate.service.ExchangeRateService;
import com.santaduck.tbd.global.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/exchange-rates")
@RequiredArgsConstructor
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    @GetMapping
    public BaseResponse<GetAllExchangeRateResponse> getAllExchangeRate() throws Exception {
        log.info("### 모든 환율 조회 요청");
        return new BaseResponse<>("00", "모든 환율 조회 성공", exchangeRateService.getAllExchangeRate());
    }

    @GetMapping("/{code}")
    public BaseResponse<GetExchangeRateResponse> getCurrentExchangeRate(@PathVariable("code") String code) throws Exception {
        log.info("### 단일 환율 조회 요청 : {}", code);
        return new BaseResponse<>("00", "조회 성공", exchangeRateService.getCurrentExchangeRate(code));
    }
}
