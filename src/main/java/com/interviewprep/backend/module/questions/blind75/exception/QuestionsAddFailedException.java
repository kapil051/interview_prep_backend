package com.interviewprep.backend.module.questions.blind75.exception;

import com.interviewprep.backend.common.constant.Constants;
import com.interviewprep.backend.common.exception.AppException;
import org.springframework.http.HttpStatus;

public class QuestionsAddFailedException extends AppException {

    public QuestionsAddFailedException(Throwable cause) {
        super(Constants.RESPONSE.get(Constants.QUESTIONS_ADD_FAILED), cause);
    }

    @Override
    public int getErrorCode() {
        return Constants.QUESTIONS_ADD_FAILED;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}