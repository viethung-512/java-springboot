package com.sotatek.ordermanagement.service;


import com.sotatek.ordermanagement.dto.request.UserLoginRequest;
import com.sotatek.ordermanagement.dto.response.UserLoginResponse;
import com.sotatek.ordermanagement.entity.User;
import com.sotatek.ordermanagement.exception.PasswordNotMatchedException;
import com.sotatek.ordermanagement.exception.UserNotFoundException;
import com.sotatek.ordermanagement.repository.UserRepository;
import com.sotatek.ordermanagement.utils.jwt.JwtUtil;
import com.sotatek.ordermanagement.utils.security.BCryptUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.time.ZonedDateTime;
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
            throw new UserNotFoundException(request.getUsername());
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
                        JwtUtil.RS256_TOKEN_HEADER,
                        "ordermanagement",
                        this.generateClaims(user),
                        "user_access_token",
                        issuedAt,
                        expiredAt);
        return null;
    }

    private Claims generateClaims(@NonNull final User user) {
        final Claims claims = Jwts.claims();
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());
        claims.put("role", user.getRole());

        return claims;
    }
}
