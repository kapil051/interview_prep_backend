package com.interviewprep.backend.module.auth.service.impl;

import com.interviewprep.backend.module.auth.dto.request.RegisterRequest;
import com.interviewprep.backend.module.auth.entity.User;
import com.interviewprep.backend.module.auth.entity.enums.Role;
import com.interviewprep.backend.module.auth.exception.EmailAlreadyExistsException;
import com.interviewprep.backend.module.auth.repository.UserRepository;
import com.interviewprep.backend.module.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .isVerified(false)
                .build();

        userRepository.save(user);
    }
}