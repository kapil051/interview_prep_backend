package com.interviewprep.backend.common.constant;

import java.util.Map;

public final class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    // Success codes (2xxx)
    public static final int USER_REGISTERED = 2000;
    public static final int LOGIN_SUCCESS = 2001;
    public static final int OTP_SENT = 2002;
    public static final int OTP_VERIFIED = 2003;

    // Failure codes (4xxx)
    public static final int INVALID_CREDENTIALS = 4000;
    public static final int EMAIL_ALREADY_EXISTS = 4001;
    public static final int USER_NOT_FOUND = 4002;
    public static final int EMAIL_NOT_VERIFIED = 4003;
    public static final int TOKEN_EXPIRED = 4004;
    public static final int TOKEN_INVALID = 4005;
    public static final int VALIDATION_FAILED = 4006;
    public static final int INTERNAL_SERVER_ERROR = 4007;
    public static final int ACCESS_DENIED = 4008;
    public static final int INVALID_ARGUMENT = 4009;
    public static final int TOKEN_MISSING = 4010;
    public static final int AUTH_ERROR = 4011;
    public static final int RESOURCE_NOT_FOUND = 4012;

    public static final Map<Integer, String> RESPONSE = Map.ofEntries(

            //success responses
            Map.entry(USER_REGISTERED, "User registered successfully"),
            Map.entry(LOGIN_SUCCESS, "Login successful"),
            Map.entry(OTP_SENT, "OTP sent successfully"),
            Map.entry(OTP_VERIFIED, "OTP verified successfully"),


            //failure responses
            Map.entry(INVALID_CREDENTIALS, "Invalid credentials"),
            Map.entry(EMAIL_ALREADY_EXISTS, "Email already exists"),
            Map.entry(USER_NOT_FOUND, "User not found"),
            Map.entry(EMAIL_NOT_VERIFIED, "Email not verified"),
            Map.entry(TOKEN_EXPIRED, "Token has expired"),
            Map.entry(TOKEN_INVALID, "Token is invalid"),
            Map.entry(VALIDATION_FAILED, "Validation failed"),
            Map.entry(INTERNAL_SERVER_ERROR, "Internal server error"),
            Map.entry(ACCESS_DENIED, "Access denied"),
            Map.entry(INVALID_ARGUMENT, "Invalid argument"),
            Map.entry(TOKEN_MISSING, "Authentication token is missing"),
            Map.entry(AUTH_ERROR, "Authentication error"),
            Map.entry(RESOURCE_NOT_FOUND, "Resource not found")
    );
}