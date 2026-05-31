package com.interviewprep.backend.module.questions.blind75.service.impl;

import com.interviewprep.backend.common.exception.AppException;
import com.interviewprep.backend.module.questions.blind75.dto.request.Blind75Request;
import com.interviewprep.backend.module.questions.blind75.dto.response.Blind75Response;
import com.interviewprep.backend.module.questions.blind75.entity.Blind75Question;
import com.interviewprep.backend.module.questions.blind75.entity.UserQuestionProgress;
import com.interviewprep.backend.module.questions.blind75.enums.ProgressStatus;
import com.interviewprep.backend.module.questions.blind75.exception.QuestionDeleteFailedException;
import com.interviewprep.backend.module.questions.blind75.exception.QuestionNotFoundException;
import com.interviewprep.backend.module.questions.blind75.exception.QuestionUpdateFailedException;
import com.interviewprep.backend.module.questions.blind75.exception.QuestionsAddFailedException;
import com.interviewprep.backend.module.questions.blind75.exception.QuestionsFetchFailedException;
import com.interviewprep.backend.module.questions.blind75.repository.Blind75Repository;
import com.interviewprep.backend.module.questions.blind75.repository.UserProgressRepository;
import com.interviewprep.backend.module.questions.blind75.service.Blind75Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class Blind75ServiceImpl implements Blind75Service {

    private final Blind75Repository blind75Repository;
    private final UserProgressRepository userProgressRepository;

    @Override
    public void addQuestions(List<Blind75Request> blind75RequestList) {
        try {
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


            blind75Repository.saveAll(newQuestions);

        } catch (AppException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Failed to save blind75 questions: {}", ex.getMessage(), ex);
            throw new QuestionsAddFailedException(ex);
        }
    }

    @Override
    public void updateQuestion(UUID id, Blind75Request request) {
        try {
            Blind75Question question = blind75Repository.findById(id).orElseThrow(QuestionNotFoundException::new);

            question.setTitle(request.getTitle());
            question.setDifficulty(request.getDifficulty());
            question.setTopic(request.getTopic());
            question.setPattern(request.getPattern());
            question.setPracticeLink(request.getPracticeLink());
            question.setVideoSolutionLink(request.getVideoSolutionLink());
            question.setVideoAvailability(request.getVideoAvailability());

            blind75Repository.save(question);

        } catch (AppException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Failed to update blind75 question with id {}: {}", id, ex.getMessage(), ex);
            throw new QuestionUpdateFailedException(ex);
        }
    }

    @Override
    public void deleteQuestion(UUID id) {
        try {
            blind75Repository.findById(id).orElseThrow(QuestionNotFoundException::new);
            blind75Repository.deleteById(id);

        } catch (AppException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Failed to delete blind75 question with id {}: {}", id, ex.getMessage(), ex);
            throw new QuestionDeleteFailedException(ex);
        }
    }

    @Override
    public List<Blind75Response> getAllQuestions(UUID userId) {
        try {
            List<Blind75Question> questions = blind75Repository.findAll();

            Map<UUID, ProgressStatus> progressMap = userProgressRepository.findByUserId(userId)
                    .stream()
                    .collect(Collectors.toMap(
                            progress -> progress.getQuestion().getId(),
                            UserQuestionProgress::getStatus
                    ));

            return questions.stream()
                    .map(question -> Blind75Response.builder()
                            .id(question.getId())
                            .title(question.getTitle())
                            .difficulty(question.getDifficulty())
                            .topic(question.getTopic())
                            .pattern(question.getPattern())
                            .practiceLink(question.getPracticeLink())
                            .videoSolutionLink(question.getVideoSolutionLink())
                            .videoAvailability(question.getVideoAvailability())
                            .status(progressMap.getOrDefault(question.getId(), ProgressStatus.NOT_STARTED))
                            .build())
                    .toList();

        } catch (AppException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Failed to fetch blind75 questions for user {}: {}", userId, ex.getMessage(), ex);
            throw new QuestionsFetchFailedException(ex);
        }
    }
}