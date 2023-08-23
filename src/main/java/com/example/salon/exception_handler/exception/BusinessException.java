package com.example.salon.exception_handler.exception;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends AbstractException {

    public BusinessException(Throwable cause, ExceptionErrorCode exceptionErrorCode, List<Object> args) {
        super(cause, exceptionErrorCode, args);
    }

    public BusinessException(ExceptionErrorCode exceptionErrorCode, List<Object> args) {
        super(exceptionErrorCode, args);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ExceptionErrorCode errorCode;
        private List<Object> args;
        private Throwable cause;

        public Builder() {
            args = new ArrayList<>();
        }

        public Builder arg(Object arg) {
            this.args.add(arg);
            return this;
        }

        public Builder args(List<Object> args) {
            this.args.addAll(args);
            return this;
        }

        public Builder code(ExceptionErrorCode errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public Builder cause(Throwable cause) {
            this.cause = cause;
            return this;
        }

        public BusinessException build() {
            return cause == null
                    ? new BusinessException(errorCode, args)
                    : new BusinessException(cause, errorCode, args);
        }
    }
}
