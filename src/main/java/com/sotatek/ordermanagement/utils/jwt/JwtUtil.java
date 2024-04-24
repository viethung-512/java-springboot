package com.sotatek.ordermanagement.utils.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.DecodingException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import lombok.AccessLevel;
import org.springframework.beans.factory.annotation.Value;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class JwtUtil {

    public static final Map<String, Object> HS512_TOKEN_HEADER =
            Map.ofEntries(Map.entry("typ", "JWT"), Map.entry("alg", "HS512"));

    private static final String jwtSecret = "SeVUMdGZGhJffiJ3jrz4GM5tTl1ZIeFBnuQb8ePzho0ieJIOxqDL8tXIZJkj9+DAq55UAHzBfqpCOHfuIAHh8g==";

    private static final SecretKey JWT_SECRET = new SecretKeySpec(Base64.getDecoder().decode(jwtSecret),
            SignatureAlgorithm.HS512.getJcaName());

    @NonNull
    public static Claims extractAllClaims(@NonNull final String jwt) throws InvalidJwtError {
        final JwtParser jwtParser;
        try {
            jwtParser = Jwts.parserBuilder().setSigningKey(JWT_SECRET).build();
        } catch (JwtException | UnsupportedOperationException | IllegalArgumentException e) {
            log.info("Jwt error happened", e);
            throw new InvalidJwtError();
        }

        try {
            return jwtParser.parseClaimsJws(jwt).getBody();
        } catch (ExpiredJwtException
                | UnsupportedJwtException
                | MalformedJwtException
                | SignatureException
                | IllegalArgumentException
                | DecodingException e) {
            log.info("Jwt error happened", e);
            throw new InvalidJwtError();
        } catch (Exception e) {
            log.error("This error should not be raised.", e);
            throw new InvalidJwtError();
        }
    }

    @NonNull
    public static String generateAccessToken(
            @NonNull final Map<String, Object> header,
            @NonNull final String issuer,
            @NonNull final Claims claims,
            @NonNull final String subject,
            @NonNull final ZonedDateTime issuedAt,
            @NonNull final ZonedDateTime expiration) {
        return Jwts.builder()
                .setHeader(header)
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(Date.from(issuedAt.toInstant()))
                .setIssuer(issuer)
                .setExpiration(Date.from(expiration.toInstant()))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }
}
