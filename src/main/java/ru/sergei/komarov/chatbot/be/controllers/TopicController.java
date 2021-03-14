package ru.sergei.komarov.chatbot.be.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sergei.komarov.chatbot.be.models.Topic;
import ru.sergei.komarov.chatbot.be.services.TopicService;

import java.util.List;

@RestController
@RequestMapping("/api/topic")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/get/all")
    public List<Topic> getAll() {
        return topicService.getAll();
    }
}
