package com.sotatek.ordermanagement.utils.jwt;

import com.sotatek.ordermanagement.entity.UserRole;
import io.jsonwebtoken.Claims;
import lombok.Builder;
import lombok.NonNull;

public class JwtUserPayload extends JwtPayload{
    private final String username;
    private final UserRole role;

    @Builder
    public JwtUserPayload(final String iss,
                          final String sub,
                          final long iat,
                          final long exp,
                          final String username,
                          final UserRole role) {
        super(iss, sub, iat, exp);
        this.username = username;
        this.role = role;
    }

    @NonNull
    public static JwtUserPayload from(@NonNull final Claims claims) {
        return JwtUserPayload.builder()
                .iss(claims.getIssuer())
                .exp(claims.getExpiration().getTime())
                .sub(claims.getSubject())
                .iat(claims.getIssuedAt().getTime())
                .username(claims.get("username", String.class))
                .role(claims.get("role", UserRole.class))
                .build();
    }
}