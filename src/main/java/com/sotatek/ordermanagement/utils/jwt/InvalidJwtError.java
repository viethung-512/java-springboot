package com.sotatek.ordermanagement.utils.jwt;

import javax.naming.AuthenticationException;

public class InvalidJwtError extends AuthenticationException {
    public InvalidJwtError() {
        super("Invalid JWT token");
    }
}
