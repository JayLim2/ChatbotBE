package ru.sergei.komarov.chatbot.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sergei.komarov.chatbot.server.models.Message;
import ru.sergei.komarov.chatbot.server.services.MessagesService;
import ru.sergei.komarov.chatbot.server.services.UsersService;
import ru.sergei.komarov.chatbot.server.utils.Converter;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/messages")
public class MessagesController {

    private final MessagesService messagesService;
    private final UsersService usersService;

    @Autowired
    public MessagesController(MessagesService messagesService, UsersService usersService) {
        this.messagesService = messagesService;
        this.usersService = usersService;
    }

    @GetMapping("/get/all")
    @ResponseBody
    public List<Map<String, Object>> getAll() {
        return Converter.messagesToMap(messagesService.getAll());
    }

    @GetMapping("/get/{userId}")
    @ResponseBody
    public List<Map<String, Object>> getByUserId(@PathVariable String userId) {
        return Converter.messagesToMap(messagesService.getByUser(
                usersService.getByLogin(userId)
        ));
    }
}
