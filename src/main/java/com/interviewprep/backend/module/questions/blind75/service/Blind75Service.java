package com.interviewprep.backend.module.questions.blind75.service;

import com.interviewprep.backend.module.questions.blind75.dto.request.Blind75Request;

import java.util.List;

public interface Blind75Service {

    void addQuestions(List<Blind75Request> requests);
}