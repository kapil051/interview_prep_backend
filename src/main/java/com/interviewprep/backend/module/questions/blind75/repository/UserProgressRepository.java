package com.interviewprep.backend.module.questions.blind75.repository;

import com.interviewprep.backend.module.questions.blind75.entity.UserQuestionProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserProgressRepository extends JpaRepository<UserQuestionProgress, UUID> {

    Optional<UserQuestionProgress> findByUserIdAndQuestionId(UUID userId, UUID questionId);

    List<UserQuestionProgress> findByUserId(UUID userId);
}