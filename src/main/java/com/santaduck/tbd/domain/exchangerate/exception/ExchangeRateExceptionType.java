package com.santaduck.tbd.domain.exchangerate.exception;

import com.santaduck.tbd.global.exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum ExchangeRateExceptionType implements BaseExceptionType {
    NOT_FOUND("1", HttpStatus.BAD_REQUEST, "환율을 찾을 수 없습니다.")
    ;

    ExchangeRateExceptionType(String errorCode, HttpStatus httpStatus, String errorMessage) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    private String errorCode;
    private HttpStatus httpStatus;
    private String errorMessage;

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
