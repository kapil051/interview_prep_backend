package com.interviewprep.backend.module.questions.blind75.exception;

import com.interviewprep.backend.common.constant.Constants;
import com.interviewprep.backend.common.exception.AppException;
import org.springframework.http.HttpStatus;

public class QuestionUpdateFailedException extends AppException {

    public QuestionUpdateFailedException(Throwable cause) {
        super(Constants.RESPONSE.get(Constants.QUESTION_UPDATE_FAILED), cause);
    }

    @Override
    public int getErrorCode() {
        return Constants.QUESTION_UPDATE_FAILED;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}