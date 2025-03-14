package ru.geekbrains.auth.server.configs;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Кодировщик паролей и client-secret.
 */
@Component
public class CustomPasswordEncoder implements PasswordEncoder {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomPasswordEncoder() {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return bCryptPasswordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }
}
