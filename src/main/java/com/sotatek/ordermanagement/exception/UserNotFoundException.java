package com.sotatek.ordermanagement.exception;

import lombok.Value;

@Value
public class UserNotFoundException extends RuntimeException {
    final String username;
}
