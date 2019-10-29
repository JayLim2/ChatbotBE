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

    @GetMapping("/createDummy")
    @ResponseBody
    public String createDummy() {

        usersService.deleteAll();

        User user = new User();
        user.setLogin("SuPeR_megatron777");
        user.setPassword(HashTool.hash("password"));
        List<Message> messages = new ArrayList<>();
        Message message1 = new Message();
        message1.setMessage("Hello dummy world!");
        message1.setDate(LocalDateTime.now());
        message1.setUser(user);
        Message message2 = new Message();
        message2.setMessage("BlaBlaCar");
        message2.setDate(LocalDateTime.now().minusDays(3).minusHours(4).minusMinutes(23));
        message2.setUser(user);
        messages.add(message1);
        messages.add(message2);
        user.setMessages(messages);
        usersService.save(user);

        return "Created.";
    }
}
