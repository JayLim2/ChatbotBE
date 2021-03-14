package ru.sergei.komarov.chatbot.be.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sergei.komarov.chatbot.be.models.ProfileConfiguration;
import ru.sergei.komarov.chatbot.be.models.User;
import ru.sergei.komarov.chatbot.be.services.ConfigService;
import ru.sergei.komarov.chatbot.be.services.UserService;

@RestController
@RequestMapping("/api/config")
public class ConfigController {

    private final UserService userService;
    private final ConfigService configService;

    public ConfigController(UserService userService, ConfigService configService) {
        this.userService = userService;
        this.configService = configService;
    }

    @PostMapping("/get/user")
    public ProfileConfiguration getConfigByUser(@RequestBody User user) {
        ProfileConfiguration config = configService.getConfigByUser(user);
        return config != null ? config : new ProfileConfiguration();
    }

    @PostMapping("/save")
    public ProfileConfiguration save(@RequestBody ProfileConfiguration configuration) {
        return configService.save(configuration);
    }

}
