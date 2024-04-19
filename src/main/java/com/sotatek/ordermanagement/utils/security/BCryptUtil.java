package com.sotatek.ordermanagement.utils.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BCryptUtil {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @NonNull
    public static String hashPassword(@NonNull final String password) {
        return encoder.encode(password);
    }

    public static boolean isPasswordMatched(@NonNull final String password, @NonNull final String hashedPassword) {
        return encoder.matches(password, hashedPassword);
    }
}
