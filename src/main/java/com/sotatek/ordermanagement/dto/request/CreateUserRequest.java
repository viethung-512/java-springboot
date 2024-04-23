package com.sotatek.ordermanagement.dto.request;


import com.sotatek.ordermanagement.entity.UserRole;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUserRequest {
    String username;
    String name;
    String email;
    String phone;
    String password;
    UserRole role;
}
