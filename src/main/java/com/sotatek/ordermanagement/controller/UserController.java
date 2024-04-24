package com.sotatek.ordermanagement.controller;


import com.sotatek.ordermanagement.dto.request.CreateUserRequest;
import com.sotatek.ordermanagement.dto.request.ResetPasswordRequest;
import com.sotatek.ordermanagement.dto.response.UserDetailsResponse;
import com.sotatek.ordermanagement.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @GetMapping("")
    @Secured({"ADMIN", "OPERATOR"})
    public List<UserDetailsResponse> getUsers() {
        return userServiceImpl.getUsers();
    }

    @PostMapping("create")
    @Secured("ADMIN")
    public UserDetailsResponse createUser(@Valid @RequestBody CreateUserRequest request) {
        return userServiceImpl.createUser(request);
    }

    @PostMapping("reset-password")
    @Secured("ADMIN")
    public UserDetailsResponse resetUserPassword(@Valid @RequestBody ResetPasswordRequest request) {
        return userServiceImpl.resetUserPassword(request);
    }

    @DeleteMapping("{userId}")
    @Secured("ADMIN")
    public UserDetailsResponse deleteUser(@PathVariable("userId") long userId) {
        return userServiceImpl.deleteUser(userId);
    }
}
