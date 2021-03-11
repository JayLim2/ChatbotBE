package ru.sergei.komarov.chatbot.be.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sergei.komarov.chatbot.be.models.Role;
import ru.sergei.komarov.chatbot.be.models.User;
import ru.sergei.komarov.chatbot.be.services.ChatService;
import ru.sergei.komarov.chatbot.be.services.MessageService;
import ru.sergei.komarov.chatbot.be.services.UserService;
import ru.sergei.komarov.chatbot.be.utils.HashTool;

@RestController
@RequestMapping("/fix")
public class FixController {

    private final HashTool hashTool;

    private final ChatService chatService;
    private final MessageService messageService;
    private final UserService userService;

    public FixController(HashTool hashTool,
                         ChatService chatService,
                         MessageService messageService,
                         UserService userService) {

        this.hashTool = hashTool;
        this.chatService = chatService;
        this.messageService = messageService;
        this.userService = userService;
    }

    @GetMapping("/init")
    @ResponseBody
    public String init() {
        chatService.deleteAll();
        messageService.deleteAll();
        userService.deleteAll();

        User user = new User();
        user.setLogin("USER");
        user.setPasswordHash(hashTool.hash("q"));
        userService.save(user);

        user = new User();
        user.setLogin("SYSTEM");
        user.setPasswordHash(hashTool.hash("q"));
        user.setRole(Role.ADMIN);
        userService.save(user);

        user = new User();
        user.setLogin("Q");
        user.setPasswordHash(hashTool.hash("q"));
        userService.save(user);

        return "All users have been created successfully.";
    }
}
