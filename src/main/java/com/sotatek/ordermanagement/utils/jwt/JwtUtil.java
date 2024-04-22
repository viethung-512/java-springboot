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
import io.jsonwebtoken.security.SignatureException;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class JwtUtil {
    public static final Map<String, Object> RS256_TOKEN_HEADER =
            Map.ofEntries(Map.entry("typ", "JWT"), Map.entry("alg", "RS256"));
    private static final String BEARER_PREFIX = "Bearer ";
    private static final String RSA_ALGORITHM = "RSA";

    private static final String JWT_SECRET = "Test 123";

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
