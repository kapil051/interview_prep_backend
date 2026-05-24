package com.interviewprep.backend.module.auth.service;

import com.interviewprep.backend.module.auth.dto.request.LoginRequest;
import com.interviewprep.backend.module.auth.dto.request.RegisterRequest;
import com.interviewprep.backend.module.auth.dto.response.LoginResponse;

public interface AuthService {

    void register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}