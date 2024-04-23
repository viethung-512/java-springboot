package com.sotatek.ordermanagement.exception;


import lombok.Value;

@Value
public class NotFoundException extends RuntimeException {
    final String message;
}
