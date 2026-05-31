package com.interviewprep.backend.module.questions.blind75.service;

import com.interviewprep.backend.module.questions.blind75.dto.request.Blind75Request;
import com.interviewprep.backend.module.questions.blind75.dto.response.Blind75Response;

import java.util.List;
import java.util.UUID;

public interface Blind75Service {

    void addQuestions(List<Blind75Request> requests);

    void updateQuestion(UUID id, Blind75Request request);

    void deleteQuestion(UUID id);

    List<Blind75Response> getAllQuestions(UUID userId);
}