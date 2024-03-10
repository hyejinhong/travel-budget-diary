package com.santaduck.tbd.domain.exchangerate.repository;

import com.santaduck.tbd.domain.exchangerate.entity.ExchangeRate;
import org.springframework.data.repository.CrudRepository;

public interface ExchangeRateRedisRepository extends CrudRepository<ExchangeRate, String> {
}
