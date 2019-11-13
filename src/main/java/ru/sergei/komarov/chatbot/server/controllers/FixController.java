package ru.sergei.komarov.chatbot.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sergei.komarov.chatbot.server.models.Message;
import ru.sergei.komarov.chatbot.server.models.User;
import ru.sergei.komarov.chatbot.server.services.MessagesService;
import ru.sergei.komarov.chatbot.server.services.UsersService;
import ru.sergei.komarov.chatbot.server.utils.HashTool;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fix")
public class FixController {

    private final MessagesService messagesService;
    private final UsersService usersService;

    @Autowired
    public FixController(MessagesService messagesService, UsersService usersService) {
        this.messagesService = messagesService;
        this.usersService = usersService;
    }

    @GetMapping("/init")
    @ResponseBody
    public String init() {

        usersService.deleteAll();

        User user = new User();
        user.setLogin("USER");
        user.setPassword(HashTool.hash("password"));
        usersService.save(user);

        user = new User();
        user.setLogin("SYSTEM");
        user.setPassword(HashTool.hash("password"));
        usersService.save(user);

        return "Created.";
    }
}
