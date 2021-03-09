package ru.sergei.komarov.chatbot.be.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sergei.komarov.chatbot.be.models.Message;
import ru.sergei.komarov.chatbot.be.services.MessagesService;
import ru.sergei.komarov.chatbot.be.services.UsersService;

import java.util.ArrayList;
import java.util.List;

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
    public List<Message> getAll() {
        return messagesService.getAll();
    }

    @PostMapping("/save")
    @ResponseBody
    public Object saveMessage(@RequestBody Message message) {
        try {
            message = messagesService.save(message);
            return message.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return"FAIL";
        }
    }

    @PostMapping("/save/all")
    @ResponseBody
    public Object saveMessages(@RequestBody List<Message> messages) {
        List<Integer> newMessagesIds = new ArrayList<>(messages.size());
        for (Message message : messages) {
            try {
                message = messagesService.save(message);
                newMessagesIds.add(message.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (messages.size() == newMessagesIds.size()) {
            return newMessagesIds;
        }

        if (newMessagesIds.size() > 0) {
            return "PARTLY_FAIL";
        } else {
            return "FAIL";
        }
    }

    @PostMapping("/delete/all")
    @ResponseBody
    public void deleteAll() {
        messagesService.deleteAll();
    }

}
