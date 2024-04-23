package com.sotatek.ordermanagement.controller;


import com.sotatek.ordermanagement.dto.request.CreateUserRequest;
import com.sotatek.ordermanagement.dto.request.UserLoginRequest;
import com.sotatek.ordermanagement.dto.response.UserDetailsResponse;
import com.sotatek.ordermanagement.dto.response.UserLoginResponse;
import com.sotatek.ordermanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public UserLoginResponse login(@RequestBody UserLoginRequest request) {
        return userService.login(request);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public UserDetailsResponse createUser(@RequestBody CreateUserRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.createUser(request);
    }
}
