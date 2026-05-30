package com.interviewprep.backend.module.questions.blind75.exception;

import com.interviewprep.backend.common.constant.Constants;
import com.interviewprep.backend.common.exception.AppException;
import org.springframework.http.HttpStatus;

public class QuestionNotFoundException extends AppException {

    public QuestionNotFoundException() {
        super(Constants.RESPONSE.get(Constants.QUESTION_NOT_FOUND));
    }

    @Override
    public int getErrorCode() {
        return Constants.QUESTION_NOT_FOUND;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}