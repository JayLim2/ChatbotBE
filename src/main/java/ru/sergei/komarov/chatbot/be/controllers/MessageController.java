package ru.sergei.komarov.chatbot.be.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.sergei.komarov.chatbot.be.integrations.AIIntegrationService;
import ru.sergei.komarov.chatbot.be.models.Advice;
import ru.sergei.komarov.chatbot.be.models.Chat;
import ru.sergei.komarov.chatbot.be.models.Message;
import ru.sergei.komarov.chatbot.be.services.AdviceService;
import ru.sergei.komarov.chatbot.be.services.MessageService;
import ru.sergei.komarov.chatbot.be.services.UserService;

import javax.xml.ws.ResponseWrapper;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;
    private final AdviceService adviceService;
    private final AIIntegrationService aiIntegrationService;

    public MessageController(MessageService messageService, UserService userService,
                             AdviceService adviceService, AIIntegrationService aiIntegrationService) {
        this.messageService = messageService;
        this.userService = userService;
        this.adviceService = adviceService;
        this.aiIntegrationService = aiIntegrationService;
    }

    @GetMapping("/get/all")
    @ResponseBody
    public List<Message> getAll() {
        return messageService.getAll();
    }

    @PostMapping(value = "/get/chat")
    public List<Message> getByChatId(@RequestBody Chat chat) {
        return messageService.getByChat(chat);
    }

    @PostMapping("/save")
    @ResponseBody
    public List<Message> saveMessage(@RequestBody Message message) {
        Message answer = aiIntegrationService.getAnswer(message);
        List<Advice> advices = answer.getAdvices();
        answer.setAdvices(null);

        message = messageService.save(message);
        answer = messageService.save(answer);

        for (Advice advice : advices) {
            advice.setMessage(message);
        }
        advices = adviceService.saveList(advices);

        message.setAdvices(advices);
        message = messageService.save(message);
        List<Message> messages = Arrays.asList(message, answer);
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
