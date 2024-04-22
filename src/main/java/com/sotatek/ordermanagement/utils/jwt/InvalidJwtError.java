package com.sotatek.ordermanagement.utils.jwt;


import org.springframework.security.core.AuthenticationException;

public class InvalidJwtError extends AuthenticationException {
    public InvalidJwtError() {
        super("Invalid JWT token");
    }
}
