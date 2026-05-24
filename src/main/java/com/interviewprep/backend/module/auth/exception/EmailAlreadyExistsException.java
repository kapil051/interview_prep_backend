package com.interviewprep.backend.module.auth.exception;

import com.interviewprep.backend.common.constant.Constants;
import com.interviewprep.backend.common.exception.AppException;
import org.springframework.http.HttpStatus;

public class EmailAlreadyExistsException extends AppException {

    public EmailAlreadyExistsException() {
        super(Constants.RESPONSE.get(Constants.EMAIL_ALREADY_EXISTS));
    }

    @Override
    public int getErrorCode() {
        return Constants.EMAIL_ALREADY_EXISTS;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.CONFLICT;
    }
}