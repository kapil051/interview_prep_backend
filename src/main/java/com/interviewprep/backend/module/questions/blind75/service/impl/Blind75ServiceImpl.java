package com.interviewprep.backend.module.questions.blind75.service.impl;

import com.interviewprep.backend.module.questions.blind75.dto.request.Blind75Request;
import com.interviewprep.backend.module.questions.blind75.entity.Blind75Question;
import com.interviewprep.backend.module.questions.blind75.exception.QuestionsAddFailedException;
import com.interviewprep.backend.module.questions.blind75.repository.Blind75Repository;
import com.interviewprep.backend.module.questions.blind75.service.Blind75Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class Blind75ServiceImpl implements Blind75Service {

    private final Blind75Repository blind75Repository;

    @Override
    public void addQuestions(List<Blind75Request> blind75RequestList) {
        List<String> incomingTitles = blind75RequestList.stream()
                .map(Blind75Request::getTitle)
                .toList();

        Set<String> existingTitles = blind75Repository.findByTitleIn(incomingTitles)
                .stream()
                .map(Blind75Question::getTitle)
                .collect(Collectors.toSet());

        List<Blind75Question> newQuestions = blind75RequestList.stream()
                .filter(blind75Request -> !existingTitles.contains(blind75Request.getTitle()))
                .map(blind75Request -> Blind75Question.builder()
                        .title(blind75Request.getTitle())
                        .difficulty(blind75Request.getDifficulty())
                        .topic(blind75Request.getTopic())
                        .pattern(blind75Request.getPattern())
                        .practiceLink(blind75Request.getPracticeLink())
                        .videoSolutionLink(blind75Request.getVideoSolutionLink())
                        .videoAvailability(blind75Request.getVideoAvailability())
                        .build())
                .toList();

        try {
            blind75Repository.saveAll(newQuestions);
        } catch (Exception ex) {
            log.error("Failed to save blind75 questions: {}", ex.getMessage(), ex);
            throw new QuestionsAddFailedException(ex);
        }
    }
}