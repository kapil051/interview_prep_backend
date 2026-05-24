package com.interviewprep.backend.module.auth.service.impl;

import com.interviewprep.backend.common.security.CustomUserDetails;
import com.interviewprep.backend.common.security.CustomUserDetailsService;
import com.interviewprep.backend.common.security.JwtService;
import com.interviewprep.backend.module.auth.dto.request.LoginRequest;
import com.interviewprep.backend.module.auth.dto.request.RegisterRequest;
import com.interviewprep.backend.module.auth.dto.response.LoginResponse;
import com.interviewprep.backend.module.auth.entity.User;
import com.interviewprep.backend.module.auth.entity.enums.Role;
import com.interviewprep.backend.module.auth.exception.EmailAlreadyExistsException;
import com.interviewprep.backend.module.auth.repository.UserRepository;
import com.interviewprep.backend.module.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtService jwtService;

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

    @Override
    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(request.getEmail());
        User user = userDetails.getUser();
        String token = jwtService.generateToken(user);

        return LoginResponse.builder()
                .token(token)
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(user.getRole().name())
                .build();
    }
}