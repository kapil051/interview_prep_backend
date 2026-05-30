package com.interviewprep.backend.module.questions.blind75.controller;

import com.interviewprep.backend.common.constant.Constants;
import com.interviewprep.backend.common.response.ApiResponse;
import com.interviewprep.backend.module.questions.blind75.dto.request.Blind75Request;
import com.interviewprep.backend.module.questions.blind75.service.Blind75Service;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/blind75")
@RequiredArgsConstructor
public class Blind75Controller {

    private final Blind75Service blind75Service;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> addQuestions(@Valid @RequestBody List<@Valid Blind75Request> requests) {
        blind75Service.addQuestions(requests);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.builder().code(Constants.QUESTIONS_ADDED_SUCCESSFULLY).success(true).build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> updateQuestion(@PathVariable UUID id, @Valid @RequestBody Blind75Request request) {
        blind75Service.updateQuestion(id, request);
        return ResponseEntity.ok(ApiResponse.builder().code(Constants.QUESTION_UPDATED_SUCCESSFULLY).success(true).build());
    }
}