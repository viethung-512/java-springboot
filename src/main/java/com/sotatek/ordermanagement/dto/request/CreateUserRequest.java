package com.sotatek.ordermanagement.dto.request;


import com.sotatek.ordermanagement.entity.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUserRequest {
    @NotEmpty String username;
    @NotEmpty String name;
    @Email @NotEmpty String email;
    @NotEmpty String phone;
    @NotEmpty String password;
    @NotEmpty UserRole role;
}
