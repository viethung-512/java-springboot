package com.sotatek.ordermanagement.security.filter;


import com.sotatek.ordermanagement.utils.jwt.InvalidJwtError;
import com.sotatek.ordermanagement.utils.jwt.JwtUserPayload;
import com.sotatek.ordermanagement.utils.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final String BEARER_PREFIX = "Bearer ";

    private final AuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!this.isBearerToken(authHeader)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String jwtToken = this.getJwtFromAuthHeader(authHeader);
        final Authentication authentication;

        try {
            authentication = this.getAuthenticationByJwt(jwtToken);
        } catch (InvalidJwtError error) {
            SecurityContextHolder.clearContext();
            this.authenticationEntryPoint.commence(request, response, new InvalidJwtError());
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    @NonNull
    private String getJwtFromAuthHeader(String authHeader) throws InvalidJwtError {
        if (StringUtils.isNotEmpty(authHeader) && authHeader.startsWith(BEARER_PREFIX)) {
            return authHeader.substring(BEARER_PREFIX.length());
        }
        throw new InvalidJwtError();
    }

    private boolean isBearerToken(@Nullable final String authHeader) {
        return StringUtils.isNotEmpty(authHeader)
                && StringUtils.startsWithIgnoreCase(authHeader, BEARER_PREFIX);
    }

    @NonNull
    private Authentication getAuthenticationByJwt(@NonNull final String jwtToken)
            throws InvalidJwtError {
        final Claims claims = JwtUtil.extractAllClaims(jwtToken);
        final JwtUserPayload jwtUserPayload = JwtUserPayload.from(claims);
        final String username = jwtUserPayload.getUsername();
        final String role = jwtUserPayload.getRole();
        final List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));
        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }
}
