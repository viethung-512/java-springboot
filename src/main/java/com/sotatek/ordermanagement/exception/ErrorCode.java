package com.sotatek.ordermanagement.exception;


import lombok.Getter;

@Getter
public enum ErrorCode {
    AUTHENTICATION_ERROR("0", "Authentication Error!"),
    PASSWORD_NOT_MATCHED("1", "Password not matched!"),
    USERNAME_EXISTS("2", "Username already exists!"),
    NOT_FOUND("3", "Not found"),
    CUSTOMER_PHONE_EXISTS("4", "Customer phone already exists!"),
    PRODUCT_NAME_EXISTS("5", "Product name already exists!"),
    PRODUCT_QUANTITY_IS_NOT_ENOUGH("6", "Product quantity is not enough!"),
    DATE_STRING_IS_NOT_CORRECT("7", "Date string is not correct!");

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
