package com.sotatek.ordermanagement.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisService {
    private static final String REDIS_STUDENT_REFIX = "REDIS_STUDENT_";
    private static final long TOKEN_TIME_TO_LIVE = 3600;
    private final RedisTemplate<String, String> redisTemplate;

    public void saveUserAccessToken(final String username, final String accessToken) {
        this.redisTemplate
                .opsForValue()
                .set(
                        REDIS_STUDENT_REFIX + username,
                        accessToken,
                        TOKEN_TIME_TO_LIVE,
                        TimeUnit.SECONDS);
    }

    public Optional<String> getUserAccessToken(final String username) {
        String accessToken = this.redisTemplate.opsForValue().get(REDIS_STUDENT_REFIX + username);
        return Optional.ofNullable(accessToken);
    }

    public void deleteUserAccessToken(final String username) {
        this.redisTemplate.delete(username);
    }
}
