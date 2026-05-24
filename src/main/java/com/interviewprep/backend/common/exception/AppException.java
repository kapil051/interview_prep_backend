package com.interviewprep.backend.common.exception;

import org.springframework.http.HttpStatus;

public abstract class AppException extends RuntimeException {

    protected AppException(String message) {
        super(message);
    }

    protected AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getErrorCode();

    public abstract HttpStatus getHttpStatus();
}