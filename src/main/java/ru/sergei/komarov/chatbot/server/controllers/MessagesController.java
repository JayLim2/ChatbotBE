package ru.sergei.komarov.chatbot.server.controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sergei.komarov.chatbot.server.models.Message;
import ru.sergei.komarov.chatbot.server.services.MessagesService;
import ru.sergei.komarov.chatbot.server.services.UsersService;
import ru.sergei.komarov.chatbot.server.utils.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @PostMapping("/save")
    @ResponseBody
    public JsonPrimitive saveMessage(@RequestBody JsonObject jsonMessage) {
        try {
            Message message = new Message();
            message.setMessage(jsonMessage.get("message").getAsString());
            message.setDate(LocalDateTime.parse(jsonMessage.get("date").getAsString(), DateTimeFormatter.ISO_DATE_TIME));
            message.setUser(usersService.getByLogin(jsonMessage.get("userId").getAsString()));
            message = messagesService.save(message);
            return new JsonPrimitive(message.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonPrimitive("FAIL");
        }
    }
}
