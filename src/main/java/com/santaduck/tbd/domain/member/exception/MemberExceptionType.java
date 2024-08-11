package com.santaduck.tbd.domain.member.exception;

import com.santaduck.tbd.global.exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum MemberExceptionType implements BaseExceptionType {
    DUPLICATE_NICKNAME("1", HttpStatus.BAD_REQUEST, "이미 존재하는 닉네임입니다."),
    DUPLICATE_EMAIL("2", HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다."),
    DUPLICATE_MEMBER_ID("3", HttpStatus.BAD_REQUEST, "이미 존재하는 아이디입니다.");

    MemberExceptionType(String errorCode, HttpStatus httpStatus, String errorMessage) {
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
