package com.sotatek.ordermanagement.controller;


import com.sotatek.ordermanagement.dto.request.CreateUserRequest;
import com.sotatek.ordermanagement.dto.request.ResetPasswordRequest;
import com.sotatek.ordermanagement.dto.request.UserLoginRequest;
import com.sotatek.ordermanagement.dto.response.UserDetailsResponse;
import com.sotatek.ordermanagement.dto.response.UserLoginResponse;
import com.sotatek.ordermanagement.service.UserService;
import java.util.List;
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
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("login")
    public UserLoginResponse login(@RequestBody UserLoginRequest request) {
        return userService.login(request);
    }

    @PostMapping("logout")
    @Secured({"ADMIN", "OPERATOR"})
    public boolean logOut() {
        return true;
    }

    @GetMapping("")
    @Secured({"ADMIN", "OPERATOR"})
    public List<UserDetailsResponse> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("create")
    @Secured("ADMIN")
    public UserDetailsResponse createUser(@RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

    @PostMapping("reset-password")
    @Secured("ADMIN")
    public UserDetailsResponse resetUserPassword(@RequestBody ResetPasswordRequest request) {
        return userService.resetUserPassword(request);
    }

    @DeleteMapping("{userId}")
    @Secured("ADMIN")
    public UserDetailsResponse deleteUser(@PathVariable("userId") long userId) {
        return userService.deleteUser(userId);
    }
}
