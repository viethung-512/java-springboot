package com.sotatek.ordermanagement.exception;


import lombok.Value;

@Value
public class ProductNameExistsException extends RuntimeException {
    String productName;
}
