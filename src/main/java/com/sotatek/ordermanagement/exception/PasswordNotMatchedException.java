package com.sotatek.ordermanagement.exception;

import lombok.Value;

@Value
public class PasswordNotMatchedException extends RuntimeException {
    final String username;
    final String password;
}
