package com.sotatek.ordermanagement.service;


import com.sotatek.ordermanagement.dto.request.CreateUserRequest;
import com.sotatek.ordermanagement.dto.request.ResetPasswordRequest;
import com.sotatek.ordermanagement.dto.request.UserLoginRequest;
import com.sotatek.ordermanagement.dto.response.UserDetailsResponse;
import com.sotatek.ordermanagement.dto.response.UserLoginResponse;
import java.util.List;

public interface UserService {
    public UserLoginResponse login(UserLoginRequest request);

    public boolean logout(String authorizationToken);

    public List<UserDetailsResponse> getUsers();

    public UserDetailsResponse createUser(CreateUserRequest request);

    public UserDetailsResponse resetUserPassword(ResetPasswordRequest request);

    public UserDetailsResponse deleteUser(long userId);

    public boolean isUsernameExists(String username);
}
