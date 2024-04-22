package com.sotatek.ordermanagement.configuration;

import com.sotatek.ordermanagement.utils.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class JwtConfig {
    public static final String RSA_ALGORITHM = "RSA";
    public static final int KEY_SIZE = 2048;

    @Bean
    @Primary
    public PrivateKey generatePrivateKey(@Value("${jwt.private-key}") final String privateKey)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Apply Base64 decoding to the private key string from aws secret manager
        final byte[] privateKeyBytes = Base64.getDecoder().decode(privateKey);
        final String privateKeyString = new String(privateKeyBytes, StandardCharsets.UTF_8);

        return JwtUtil.extractRSAPrivateKey(privateKeyString);
    }

    @Bean
    @Primary
    public PublicKey generatePublicKey(@Value("${jwt.public-key}") final String publicKey)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        final byte[] publicKeyBytes = Base64.getDecoder().decode(publicKey);
        final String publicKeyString = new String(publicKeyBytes, StandardCharsets.UTF_8);
        return JwtUtil.extractRSAPublicKey(publicKeyString);
    }
}
