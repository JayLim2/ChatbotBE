package ru.sergei.komarov.chatbot.be.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.sergei.komarov.chatbot.be.models.User;
import ru.sergei.komarov.chatbot.be.services.UserService;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserController(UserService userService,
                          BCryptPasswordEncoder passwordEncoder) {

        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
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

    @PutMapping("/register")
    public User register(@RequestBody User user) {
        boolean isAlreadyRegistered = userService.isAlreadyRegistered(user);
        if (!isAlreadyRegistered) {
            user.setPasswordHash(passwordEncoder.encode(user.getPassword()));
            return userService.save(user);
        }
        return new User();
    }

    @PostMapping("/delete/all")
    public void deleteAll() {
        userService.deleteAll();
    }

    @GetMapping("/logout")
    public void logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

}
