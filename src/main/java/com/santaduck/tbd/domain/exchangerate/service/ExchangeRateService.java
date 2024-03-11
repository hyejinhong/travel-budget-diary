package com.santaduck.tbd.domain.exchangerate.service;

import com.santaduck.tbd.domain.exchangerate.dto.FetchedExchangeRateInfo;
import com.santaduck.tbd.domain.exchangerate.dto.FetchedExchangeRateVo;
import com.santaduck.tbd.domain.exchangerate.dto.GetExchangeRateResponse;
import com.santaduck.tbd.domain.exchangerate.entity.ExchangeRate;
import com.santaduck.tbd.domain.exchangerate.repository.ExchangeRateRedisRepository;
import com.santaduck.tbd.domain.exchangerate.util.ExchangeRateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExchangeRateService {

    private final ExchangeRateUtil exchangeRateUtil;
    private final ExchangeRateRedisRepository repository;

    public GetExchangeRateResponse getCurrentExchangeRate(String code) throws Exception {
        // 캐시에 저장된 값 없는 경우 새로 받아옴
        if (repository.findById(code).isEmpty()) {
            log.info("캐시에 저장된 값 없어 API 호출");
            FetchedExchangeRateInfo fetched = exchangeRateUtil.fetchExchangeRates();

            // 캐시에 저장
            for (FetchedExchangeRateVo rate : fetched.getExchangeRates()) {
                ExchangeRate e = ExchangeRate.builder()
                        .currencyCode(rate.getCurUnit())
                        .currencyName(rate.getCurNm())
                        .ttb(Double.parseDouble(rate.getTtb().replaceAll(",", "")))
                        .tts(Double.parseDouble(rate.getTts().replaceAll(",", "")))
                        .tts(Double.parseDouble(rate.getTts().replaceAll(",", "")))
                        .dealBaseRate(Double.parseDouble(rate.getDealBasR().replaceAll(",", "")))
                        .bookPrice(Double.parseDouble(rate.getBkpr().replaceAll(",", "")))
                        .yearFeeRate(Double.parseDouble(rate.getYyEfeeR().replaceAll(",", "")))
                        .tenDaysFeeRate(Double.parseDouble(rate.getTenDdEfeeR().replaceAll(",", "")))
                        .seoulDealBaseRate(Double.parseDouble(rate.getKftcDealBasR().replaceAll(",", "")))
                        .seoulBookPrice(Double.parseDouble(rate.getKftcBkpr().replaceAll(",", "")))
                        .modifiedAt(LocalDateTime.now())
                        .build();

                repository.save(e);
            }
        }

        ExchangeRate exchangeRate = repository.findById(code).get();
        return GetExchangeRateResponse.builder()
                .currencyCode(exchangeRate.getCurrencyCode())
                .currencyName(exchangeRate.getCurrencyName())
                .exchangeRate(exchangeRate.getTts())
                .modifiedAt(exchangeRate.getModifiedAt())
                .build();
    }
}
