package ru.sergei.komarov.chatbot.be.controllers;

import org.springframework.web.bind.annotation.*;
import ru.sergei.komarov.chatbot.be.integrations.AIIntegrationService;
import ru.sergei.komarov.chatbot.be.models.Message;
import ru.sergei.komarov.chatbot.be.services.MessageService;
import ru.sergei.komarov.chatbot.be.services.UserService;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;
    private final AIIntegrationService aiIntegrationService;

    public MessageController(MessageService messageService,
                             UserService userService,
                             AIIntegrationService aiIntegrationService) {

        this.messageService = messageService;
        this.userService = userService;
        this.aiIntegrationService = aiIntegrationService;
    }

    @GetMapping("/get/all")
    @ResponseBody
    public List<Message> getAll() {
        return messageService.getAll();
    }

    @PostMapping("/save")
    @ResponseBody
    public List<Message> saveMessage(@RequestBody Message message) {
        Message answer = aiIntegrationService.getAnswer(message);
        List<Message> messages = Arrays.asList(message, answer);
        messages = messageService.saveList(messages);
        return messages;
    }

    @PostMapping("/save/all")
    @ResponseBody
    public List<Message> saveMessages(@RequestBody List<Message> messages) {
        messages = messageService.saveList(messages);
        return messages;
    }

    @PostMapping("/delete/all")
    @ResponseBody
    public void deleteAll() {
        messageService.deleteAll();
    }

}
