package com.sotatek.ordermanagement.exception;


import lombok.Getter;

@Getter
public enum ErrorCode {
    AUTHENTICATION_ERROR("0", "Authentication Error!"),
    PASSWORD_NOT_MATCHED("1", "Password not matched!"),
    USERNAME_EXISTS("2", "Username already exists!"),
    USER_NOT_FOUND("3", "User not found");

    private final String errorCode;
    private final String errorMessage;

    ErrorCode(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return errorCode + ": " + errorMessage;
    }
}
