package ru.sergei.komarov.chatbot.be.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sergei.komarov.chatbot.be.integrations.HttpClient;

import java.util.HashMap;

@RestController
@RequestMapping("/api/hc")
public class HealthCheckController {

    @Value("${ai.service}")
    private String aiService;

    @Value("${be.service.version}")
    private String version;

    private final HttpClient httpClient;

    public HealthCheckController(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @GetMapping("/version")
    public String getServiceVersion() {
        String aiVersion = httpClient.get(
                aiService + "/api/hc/version",
                new HashMap<>(),
                String.class
        );
        return "BE version: " + version + ", " + aiVersion;
    }

}
