package com.sotatek.ordermanagement.controller;


import com.sotatek.ordermanagement.dto.request.UserLoginRequest;
import com.sotatek.ordermanagement.dto.response.UserLoginResponse;
import com.sotatek.ordermanagement.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserServiceImpl userServiceImpl;

    @PostMapping("login")
    public UserLoginResponse login(@RequestBody UserLoginRequest request) {
        return userServiceImpl.login(request);
    }

    @PostMapping("logout")
    @SecurityRequirement(name = "bearerAuth")
    @Secured({"ADMIN", "OPERATOR"})
    public boolean logOut() {
        return true;
    }
}
