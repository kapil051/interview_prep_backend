package com.interviewprep.backend.module.questions.blind75.exception;

import com.interviewprep.backend.common.constant.Constants;
import com.interviewprep.backend.common.exception.AppException;
import org.springframework.http.HttpStatus;

public class ProgressUpdateFailedException extends AppException {

    public ProgressUpdateFailedException(Throwable cause) {
        super(Constants.RESPONSE.get(Constants.PROGRESS_UPDATE_FAILED), cause);
    }

    @Override
    public int getErrorCode() {
        return Constants.PROGRESS_UPDATE_FAILED;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}