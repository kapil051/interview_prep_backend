package com.interviewprep.backend.module.questions.blind75.exception;

import com.interviewprep.backend.common.constant.Constants;
import com.interviewprep.backend.common.exception.AppException;
import org.springframework.http.HttpStatus;

public class QuestionDeleteFailedException extends AppException {

    public QuestionDeleteFailedException(Throwable cause) {
        super(Constants.RESPONSE.get(Constants.QUESTION_DELETE_FAILED), cause);
    }

    @Override
    public int getErrorCode() {
        return Constants.QUESTION_DELETE_FAILED;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}