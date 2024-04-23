package com.sotatek.ordermanagement.exception;

import lombok.Value;

@Value
public class UserNameExistsException extends RuntimeException {
    String username;
}
