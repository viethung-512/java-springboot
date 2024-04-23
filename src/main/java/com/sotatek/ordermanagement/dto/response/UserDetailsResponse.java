package com.sotatek.ordermanagement.dto.response;


import com.sotatek.ordermanagement.entity.User;
import com.sotatek.ordermanagement.entity.UserRole;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDetailsResponse {
    Long id;
    String username;
    String name;
    String phone;
    String email;
    UserRole role;

    public static UserDetailsResponse from(final User user) {
        return UserDetailsResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRole())
                .build();
    }
}
