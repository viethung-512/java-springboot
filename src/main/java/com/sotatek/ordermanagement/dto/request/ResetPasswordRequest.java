package com.sotatek.ordermanagement.dto.request;


import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ResetPasswordRequest {
    @NotEmpty
    String username;
    @NotEmpty
    String updatedPassword;
}
