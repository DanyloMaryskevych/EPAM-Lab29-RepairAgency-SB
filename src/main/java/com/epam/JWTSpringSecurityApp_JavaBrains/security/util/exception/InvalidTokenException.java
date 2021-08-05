package com.epam.JWTSpringSecurityApp_JavaBrains.security.util.exception;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String message) {
        super(message);
    }
}
