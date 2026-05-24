package com.interviewprep.backend.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interviewprep.backend.common.constant.Constants;
import com.interviewprep.backend.common.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String jwtError = (String) request.getAttribute("JWT_ERROR");
        log.warn("Unauthorized request to {}: {}", request.getRequestURI(), jwtError != null ? jwtError : authException.getMessage());

        int errorCode = resolveErrorCode(jwtError);
        ApiResponse apiResponse = ApiResponse.builder()
                .code(errorCode)
                .success(false)
                .build();

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getOutputStream(), apiResponse);
    }

    private int resolveErrorCode(String jwtError) {
        if (jwtError == null) return Constants.TOKEN_MISSING;
        return switch (jwtError) {
            case "TOKEN_EXPIRED" -> Constants.TOKEN_EXPIRED;
            case "TOKEN_INVALID" -> Constants.TOKEN_INVALID;
            case "USER_NOT_FOUND" -> Constants.USER_NOT_FOUND;
            default -> Constants.AUTH_ERROR;
        };
    }
}