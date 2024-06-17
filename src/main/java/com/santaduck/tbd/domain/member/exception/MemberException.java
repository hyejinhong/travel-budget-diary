package com.santaduck.tbd.domain.member.exception;

import com.santaduck.tbd.global.exception.BaseException;
import com.santaduck.tbd.global.exception.BaseExceptionType;

public class MemberException extends BaseException {
    private BaseExceptionType exceptionType;

    public MemberException(BaseExceptionType baseExceptionType) {
        this.exceptionType = baseExceptionType;
    }
    @Override
    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}
