package com.sotatek.ordermanagement.service;


import com.sotatek.ordermanagement.dto.request.CreateUserRequest;
import com.sotatek.ordermanagement.dto.request.ResetPasswordRequest;
import com.sotatek.ordermanagement.dto.request.UserLoginRequest;
import com.sotatek.ordermanagement.dto.response.UserDetailsResponse;
import com.sotatek.ordermanagement.dto.response.UserLoginResponse;
import com.sotatek.ordermanagement.entity.User;
import com.sotatek.ordermanagement.exception.NotFoundException;
import com.sotatek.ordermanagement.exception.PasswordNotMatchedException;
import com.sotatek.ordermanagement.exception.UserNameExistsException;
import com.sotatek.ordermanagement.repository.UserRepository;
import com.sotatek.ordermanagement.utils.jwt.JwtUtil;
import com.sotatek.ordermanagement.utils.security.BCryptUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserLoginResponse login(UserLoginRequest request) {
        final User user = userRepository.findByUsername(request.getUsername());
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        if (!BCryptUtil.isPasswordMatched(request.getPassword(), user.getPassword())) {
            throw new PasswordNotMatchedException(request.getUsername(), request.getPassword());
        }

        // Generate token
        // TODO: Need to add the logic implement get the token from redis cache here

        final ZonedDateTime issuedAt = ZonedDateTime.now();
        final ZonedDateTime expiredAt = issuedAt.plusHours(2);

        String token =
                JwtUtil.generateAccessToken(
                        JwtUtil.HS512_TOKEN_HEADER,
                        "ordermanagement",
                        this.generateClaims(user),
                        "user_access_token",
                        issuedAt,
                        expiredAt);

        return UserLoginResponse.builder()
                .username(request.getUsername())
                .accessToken(token)
                .build();
    }

    public List<UserDetailsResponse> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserDetailsResponse::from).toList();
    }

    public UserDetailsResponse createUser(CreateUserRequest request) {
        if (isUsernameExists(request.getUsername())) {
            throw new UserNameExistsException(request.getUsername());
        }

        final String hashedPassword = BCryptUtil.hashPassword(request.getPassword());
        final User user =
                User.builder()
                        .username(request.getUsername())
                        .name(request.getName())
                        .email(request.getEmail())
                        .phone(request.getPhone())
                        .role(request.getRole().toString())
                        .password(hashedPassword)
                        .build();
        final User savedUser = userRepository.save(user);
        return UserDetailsResponse.from(savedUser);
    }

    public UserDetailsResponse resetUserPassword(ResetPasswordRequest request) {
        User user = userRepository.findByUsername(request.getUsername());
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        final String hashedPassword = BCryptUtil.hashPassword(request.getUpdatedPassword());
        user.setPassword(hashedPassword);
        final User savedUser = userRepository.save(user);
        return UserDetailsResponse.from(savedUser);
    }

    public UserDetailsResponse deleteUser(long userId) {
        final User user = userRepository.findById(userId);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        userRepository.deleteById(userId);
        return UserDetailsResponse.from(user);
    }

    private boolean isUsernameExists(String username) {
        return userRepository.findByUsername(username) != null;
    }

    private Claims generateClaims(@NonNull final User user) {
        final Claims claims = Jwts.claims();
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());
        claims.put("role", user.getRole());

        return claims;
    }
}
