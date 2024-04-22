package com.sotatek.ordermanagement.dto.response;


import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserLoginResponse {
    String username;
    String accessToken;
}
