package com.santaduck.tbd.domain.exchangerate.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Builder
@RedisHash(value = "exchangeRate", timeToLive = 600)
public class ExchangeRate {
    @Id
    private String currencyCode;
    private String currencyName;
    private Double ttb; // 전신환매도율
    private Double tts; // 전신환매입율
    private Double dealBaseRate; // 매매기준율
    private Double bookPrice; // 장부가격
    private Double yearFeeRate; // 년환가료율
    private Double tenDaysFeeRate; // 10일환가료율
    private Double seoulDealBaseRate; // 서울외국환중개매매기준율
    private Double seoulBookPrice; // 서울외국환중개장부가격
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
