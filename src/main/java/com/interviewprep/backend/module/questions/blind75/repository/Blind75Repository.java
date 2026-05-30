package com.interviewprep.backend.module.questions.blind75.repository;

import com.interviewprep.backend.module.questions.blind75.entity.Blind75Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface Blind75Repository extends JpaRepository<Blind75Question, UUID> {
}