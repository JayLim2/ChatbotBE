package ru.sergei.komarov.chatbot.be.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class HashTool {

    private final BCryptPasswordEncoder encoder;

    @Autowired
    public HashTool(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public String hash(String value) {
        return encoder.encode(value);
    }

    public boolean isNullOrEmptyString(String string) {
        return string == null || string.trim().isEmpty();
    }
}
