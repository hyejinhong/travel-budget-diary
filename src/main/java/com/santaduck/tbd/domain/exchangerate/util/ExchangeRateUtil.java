package com.santaduck.tbd.domain.exchangerate.util;

import com.santaduck.tbd.domain.exchangerate.dto.FetchedExchangeRateVo;
import com.santaduck.tbd.domain.exchangerate.dto.FetchedExchangeRateInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExchangeRateUtil {
    private final RestTemplate restTemplate;
    private final String url = "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON";

    @Value("${api-key.exchange-rate}")
    private String apiKey;

    /**
     * 한국 수출입은행 Open API에서 환율을 받아온다.
     */
    public FetchedExchangeRateInfo fetchExchangeRates() throws Exception {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("www.koreaexim.go.kr")
                .path("/site/program/financial/exchangeJSON")
                .queryParam("authkey", apiKey)
                .queryParam("data", "AP01")
                .build(true);

        log.info("요청 uri : {}", uriComponents.toString());

        FetchedExchangeRateVo[] infos = new FetchedExchangeRateVo[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date = LocalDate.now();
        int count = 0; // 무한루프 방어
        while (infos.length == 0 && count < 30) {
            String dateStr = date.format(formatter);
            String uri = UriComponentsBuilder.fromUri(uriComponents.toUri()).queryParam("searchdate", dateStr).build().toString();

            log.info("search date : {}", dateStr);
            infos = restTemplate.getForObject(uri, FetchedExchangeRateVo[].class);

            // 영업일 나올때까지 -1일 처리
            date = date.minusDays(1);
            count++;
        }
        assert infos != null;
        log.info(Arrays.toString(infos));

        FetchedExchangeRateInfo info = new FetchedExchangeRateInfo(Arrays.asList(infos));
        return info;
    }
}
