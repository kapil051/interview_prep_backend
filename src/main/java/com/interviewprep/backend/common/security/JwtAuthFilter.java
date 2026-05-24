package com.interviewprep.backend.common.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = authHeader.substring(7);
            String email = jwtService.extractEmail(token);
            CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(email);

            if (jwtService.validateToken(token, userDetails.getUser())) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        } catch (ExpiredJwtException ex) {
            log.warn("JWT expired for request {}: {}", request.getRequestURI(), ex.getMessage());
            request.setAttribute("JWT_ERROR", "TOKEN_EXPIRED");
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException ex) {
            log.warn("Invalid JWT for request {}: {}", request.getRequestURI(), ex.getMessage());
            request.setAttribute("JWT_ERROR", "TOKEN_INVALID");
        } catch (UsernameNotFoundException ex) {
            log.warn("User not found during JWT auth for request {}: {}", request.getRequestURI(), ex.getMessage());
            request.setAttribute("JWT_ERROR", "USER_NOT_FOUND");
        } catch (IllegalArgumentException ex) {
            log.warn("Invalid token argument for request {}", request.getRequestURI());
            request.setAttribute("JWT_ERROR", "TOKEN_INVALID");
        } catch (Exception ex) {
            log.warn("Unexpected error for request {}", request.getRequestURI());
            request.setAttribute("JWT_ERROR", "TOKEN_INVALID");
        }

        filterChain.doFilter(request, response);
    }
}