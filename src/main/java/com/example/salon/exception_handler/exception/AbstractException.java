package com.example.salon.exception_handler.exception;

import lombok.Getter;

import java.util.List;

@Getter
public abstract class AbstractException extends RuntimeException {
    private String errorCode;
    private List<Object> args;

    public AbstractException(Throwable cause, ExceptionErrorCode exceptionErrorCode, List<Object> args) {
        super(cause);
        init(exceptionErrorCode, args);
    }

    public AbstractException(ExceptionErrorCode exceptionErrorCode, List<Object> args) {
        super(exceptionErrorCode.getCode());
        init(exceptionErrorCode, args);
    }

    private void init(ExceptionErrorCode exceptionErrorCode, List<Object> args) {
        this.errorCode = exceptionErrorCode.getCode();
        this.args = args;
    }
}
