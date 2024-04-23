package com.sotatek.ordermanagement.exception;


import lombok.Value;

@Value
public class CustomerPhoneExistsException extends RuntimeException {
    String phone;
}
