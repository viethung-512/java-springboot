package com.sotatek.ordermanagement.exception;


import lombok.Value;

@Value
public class ProductQuantityIsNotEnoughException extends RuntimeException {
    String productName;
}
