package ru.sergei.komarov.chatbot.be.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hc")
public class HealthCheckController {

    @Value("${be.version}")
    private String version;

    @GetMapping("/version")
    public String getServiceVersion() {
        return version;
    }

}
