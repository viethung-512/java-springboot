package com.sotatek.ordermanagement.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BaseErrorResponse {
    private long errorCode;
    private String errorMessage;
}
