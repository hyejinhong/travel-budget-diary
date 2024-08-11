package com.santaduck.tbd.domain.exchangerate.exception;

import com.santaduck.tbd.global.exception.BaseException;
import com.santaduck.tbd.global.exception.BaseExceptionType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExchangeRateException extends BaseException {

    private BaseExceptionType exceptionType;

    @Override
    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}
