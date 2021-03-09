package ru.sergei.komarov.chatbot.be.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sergei.komarov.chatbot.be.models.User;
import ru.sergei.komarov.chatbot.be.services.UsersService;

import java.util.List;

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
    public List<User> getAll() {
        return usersService.getAll();
    }

    @PostMapping("/validate/credentials")
    @ResponseBody
    public boolean validateCredentials(String login, String password) {
        return usersService.validateCredentials(login, password);
    }

    @PostMapping("/delete/all")
    public void deleteAll() {
        usersService.deleteAll();
    }

}
