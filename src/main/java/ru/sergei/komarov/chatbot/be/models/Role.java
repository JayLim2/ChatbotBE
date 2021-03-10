package ru.sergei.komarov.chatbot.be.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    GUEST, STUDENT, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
