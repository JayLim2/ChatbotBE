package ru.sergei.komarov.chatbot.be.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sergei.komarov.chatbot.be.models.Role;
import ru.sergei.komarov.chatbot.be.models.Topic;
import ru.sergei.komarov.chatbot.be.models.User;
import ru.sergei.komarov.chatbot.be.services.*;
import ru.sergei.komarov.chatbot.be.utils.HashTool;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/fix")
public class FixController {

    private final HashTool hashTool;

    private final ChatService chatService;
    private final MessageService messageService;
    private final UserService userService;
    private final TopicService topicService;
    private final ConfigService configService;

    public FixController(HashTool hashTool, ChatService chatService, MessageService messageService,
                         UserService userService, TopicService topicService, ConfigService configService) {
        this.hashTool = hashTool;
        this.chatService = chatService;
        this.messageService = messageService;
        this.userService = userService;
        this.topicService = topicService;
        this.configService = configService;
    }

    @GetMapping("/init")
    @ResponseBody
    public String init() {
        SecurityContextHolder.getContext().setAuthentication(null);

        configService.deleteAll();
        topicService.deleteAll();
        chatService.deleteAll();
        messageService.deleteAll();
        userService.deleteAll();

        User user;

        user = new User();
        user.setLogin("SYSTEM");
        user.setPasswordHash(hashTool.hash("q"));
        user.setRole(Role.ADMIN);
        userService.save(user);

        user = new User();
        user.setLogin("USER");
        user.setPasswordHash(hashTool.hash("q"));
        userService.save(user);

        user = new User();
        user.setLogin("q");
        user.setPasswordHash(hashTool.hash("q"));
        userService.save(user);

        List<String> topicStrings = Arrays.asList(
                "Путешествия", "Еда", "Кино и сериалы",
                "Автомобили", "Спорт"
        );
        List<Topic> topics = topicStrings.stream().map(str -> {
            Topic topic = new Topic();
            topic.setName(str);
            return topic;
        }).collect(Collectors.toList());
        topicService.saveList(topics);

        return "All users have been created successfully.";
    }
}
