package com.sotatek.ordermanagement.exception.dto;

import lombok.Value;

public class UserNameExistsErrorResponse extends BaseErrorResponse {
    final ErrorData errorData;

    public UserNameExistsErrorResponse(final String username) {
        super(1111, "Username `" + username + "` already exists!");
        this.errorData = new ErrorData(username);
    }

    @Value
    public static class ErrorData {
        private final String username;
    }
}
