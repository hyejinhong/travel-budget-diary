package com.santaduck.tbd.domain.exchangerate.service;

import com.santaduck.tbd.domain.exchangerate.dto.FetchedExchangeRateInfo;
import com.santaduck.tbd.domain.exchangerate.dto.FetchedExchangeRateVo;
import com.santaduck.tbd.domain.exchangerate.dto.GetAllExchangeRateResponse;
import com.santaduck.tbd.domain.exchangerate.dto.GetExchangeRateResponse;
import com.santaduck.tbd.domain.exchangerate.entity.ExchangeRate;
import com.santaduck.tbd.domain.exchangerate.exception.ExchangeRateException;
import com.santaduck.tbd.domain.exchangerate.exception.ExchangeRateExceptionType;
import com.santaduck.tbd.domain.exchangerate.repository.ExchangeRateRedisRepository;
import com.santaduck.tbd.domain.exchangerate.util.ExchangeRateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExchangeRateService {

    private final ExchangeRateUtil exchangeRateUtil;
    private final ExchangeRateRedisRepository repository;

    /**
     * 모든 환율 조회
     * */
    public GetAllExchangeRateResponse getAllExchangeRate() throws Exception {
        List<GetExchangeRateResponse> exchangeList = new ArrayList<>();

        // 캐시가 비어있는 경우
        log.info("repository.count : {}", repository.count());
        log.info("repository : {}", repository.findAll().toString());
        if (repository.findAll().iterator().hasNext()) {
            callApi();
        }

        for (ExchangeRate exchangeRate : repository.findAll()) {
            GetExchangeRateResponse item = GetExchangeRateResponse.builder()
                    .currencyCode(exchangeRate.getCurrencyCode())
                    .currencyName(exchangeRate.getCurrencyName())
                    .exchangeRate(exchangeRate.getTtb())
                    .modifiedAt(exchangeRate.getModifiedAt())
                    .build();

            exchangeList.add(item);
        }

        return GetAllExchangeRateResponse.builder()
                .size(exchangeList.size())
                .exchangeRates(exchangeList)
                .build();
    }

    /**
     * 현재 환율 조회
     * */
    public GetExchangeRateResponse getCurrentExchangeRate(String code) throws Exception {
        // 캐시에 저장된 값 없는 경우 새로 받아옴
        if (repository.findById(code).isEmpty()) {
            callApi();
        }

        Optional<ExchangeRate> exchangeRateOptional = repository.findById(code);
        if (exchangeRateOptional.isEmpty())
            throw new ExchangeRateException(ExchangeRateExceptionType.NOT_FOUND);

        ExchangeRate exchangeRate = exchangeRateOptional.get();
        return GetExchangeRateResponse.builder()
                .currencyCode(exchangeRate.getCurrencyCode())
                .currencyName(exchangeRate.getCurrencyName())
                .exchangeRate(exchangeRate.getTts())
                .modifiedAt(exchangeRate.getModifiedAt())
                .build();
    }

    private void callApi() throws Exception {
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
}
