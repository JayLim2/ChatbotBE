package ru.sergei.komarov.chatbot.be.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sergei.komarov.chatbot.be.models.User;
import ru.sergei.komarov.chatbot.be.services.UserService;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get")
    public User getUser(@RequestHeader("Authorization") String authorization) {
        String login = new String(Base64.getDecoder().decode(
                authorization.replace("Basic ", "")
        )).split(":")[0];
        System.out.println("Get user with login = " + login);
        return (User)userService.loadUserByUsername(login);
    }

    @GetMapping("/get/all")
    public List<User> getAll() {
        return userService.getAll();
    }

    @PostMapping("/delete/all")
    public void deleteAll() {
        userService.deleteAll();
    }

}
