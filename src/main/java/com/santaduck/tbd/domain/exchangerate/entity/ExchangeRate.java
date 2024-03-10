package com.santaduck.tbd.domain.exchangerate.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Getter
@Builder
@RedisHash(value = "exchangeRate", timeToLive = 120)
public class ExchangeRate {
    @Id
    private String currencyCode;
    private Double rate;
    private LocalDateTime createdAt;
}
