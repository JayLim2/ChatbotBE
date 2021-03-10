package ru.sergei.komarov.chatbot.be.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sergei.komarov.chatbot.be.models.User;
import ru.sergei.komarov.chatbot.be.services.MessageService;
import ru.sergei.komarov.chatbot.be.services.UsersService;
import ru.sergei.komarov.chatbot.be.utils.HashTool;

@RestController
@RequestMapping("/fix")
public class FixController {

    private final MessageService messageService;
    private final UsersService usersService;

    @Autowired
    public FixController(MessageService messageService, UsersService usersService) {
        this.messageService = messageService;
        this.usersService = usersService;
    }

    @GetMapping("/init")
    @ResponseBody
    public String init() {

        messageService.deleteAll();
        usersService.deleteAll();

        User user = new User();
        user.setLogin("USER");
        user.setPasswordHash(HashTool.hash("password"));
        usersService.save(user);

        user = new User();
        user.setLogin("SYSTEM");
        user.setPasswordHash(HashTool.hash("password"));
        usersService.save(user);

        user = new User();
        user.setLogin("Q");
        user.setPasswordHash(HashTool.hash("q"));
        usersService.save(user);

        return "Created.";
    }
}
