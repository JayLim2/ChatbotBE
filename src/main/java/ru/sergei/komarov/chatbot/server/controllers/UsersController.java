package ru.sergei.komarov.chatbot.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sergei.komarov.chatbot.server.models.User;
import ru.sergei.komarov.chatbot.server.services.UsersService;
import ru.sergei.komarov.chatbot.server.utils.Converter;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/get/all")
    @ResponseBody
    public List<Map<String, Object>> getAll() {
        return Converter.usersToMap(usersService.getAll());
    }

    @PostMapping("/validate/credentials")
    @ResponseBody
    public boolean validateCredentials(String login, String password) {
        return usersService.validateCredentials(login, password);
    }

}
