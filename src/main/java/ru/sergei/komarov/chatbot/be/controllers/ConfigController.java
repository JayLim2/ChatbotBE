package ru.sergei.komarov.chatbot.be.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sergei.komarov.chatbot.be.models.ProfileConfiguration;
import ru.sergei.komarov.chatbot.be.models.User;
import ru.sergei.komarov.chatbot.be.services.ConfigService;

@RestController
@RequestMapping("/api/configs")
public class ConfigController {

    private final ConfigService configService;

    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }

    @PostMapping("/get/user")
    public ProfileConfiguration getConfigByUser(@RequestBody User user) {
        return configService.getConfigByUser(user);
    }
}
