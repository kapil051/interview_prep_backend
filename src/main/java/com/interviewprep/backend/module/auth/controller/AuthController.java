package com.interviewprep.backend.module.auth.controller;

import com.interviewprep.backend.common.constant.Constants;
import com.interviewprep.backend.common.response.ApiResponse;
import com.interviewprep.backend.module.auth.dto.request.RegisterRequest;
import com.interviewprep.backend.module.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.builder().code(Constants.USER_REGISTERED).success(true).build());
    }
}