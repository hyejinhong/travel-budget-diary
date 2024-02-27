package com.santaduck.tbd.domain.exchange.util;

import com.santaduck.tbd.domain.exchange.dto.ExchangeRate;
import com.santaduck.tbd.domain.exchange.dto.FetchedExchangeRateInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.text.SimpleDateFormat;
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
//        RestTemplate restTemplate = new RestTemplate();
        StringBuilder uriBuilder = new StringBuilder(url);
        uriBuilder.append("?authkey=" + apiKey);
        uriBuilder.append("&data=AP01");
        uriBuilder.append(String.format(String.format("&searchdate=" + new SimpleDateFormat("yyyyMMdd").format(new Date()))));

        URI uri = new URI(uriBuilder.toString());
        log.info("요청 uri : {}", uri);

        ExchangeRate[] info = restTemplate.getForObject(uri, ExchangeRate[].class);

        assert info != null;
        log.info(Arrays.toString(info));
        log.info(String.valueOf(info.length));
        return null;

    }
}
