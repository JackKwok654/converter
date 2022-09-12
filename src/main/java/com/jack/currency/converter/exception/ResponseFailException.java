package com.jack.currency.converter.exception;

public class ResponseFailException extends CommonException {
    public ResponseFailException() {
    }

    public ResponseFailException(String message) {
        super(message);
    }

    public ResponseFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResponseFailException(Throwable cause) {
        super(cause);
    }

    public ResponseFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
