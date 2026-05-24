package com.interviewprep.backend.module.auth.service;

import com.interviewprep.backend.module.auth.dto.request.RegisterRequest;

public interface AuthService {

    void register(RegisterRequest request);
}