package com.interviewprep.backend.module.questions.blind75.exception;

import com.interviewprep.backend.common.constant.Constants;
import com.interviewprep.backend.common.exception.AppException;
import org.springframework.http.HttpStatus;

public class QuestionsFetchFailedException extends AppException {

    public QuestionsFetchFailedException(Throwable cause) {
        super(Constants.RESPONSE.get(Constants.QUESTIONS_FETCH_FAILED), cause);
    }

    @Override
    public int getErrorCode() {
        return Constants.QUESTIONS_FETCH_FAILED;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}