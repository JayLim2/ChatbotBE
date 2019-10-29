package ru.sergei.komarov.chatbot.server.controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sergei.komarov.chatbot.server.models.Message;
import ru.sergei.komarov.chatbot.server.services.MessagesService;
import ru.sergei.komarov.chatbot.server.services.UsersService;
import ru.sergei.komarov.chatbot.server.utils.Converter;
import ru.sergei.komarov.chatbot.server.utils.GsonConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
            Message message = parseMessageFromJson(jsonMessage);
            message = messagesService.save(message);
            return new JsonPrimitive(message.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonPrimitive("FAIL");
        }
    }

    @PostMapping("/save/all")
    @ResponseBody
    public JsonElement saveMessages(@RequestBody JsonArray jsonMessages) {
        List<Integer> newMessagesIds = new ArrayList<>(jsonMessages.size());
        for (JsonElement jsonMessageRaw : jsonMessages) {
            try {
                JsonObject jsonMessage = jsonMessageRaw.getAsJsonObject();
                Message message = parseMessageFromJson(jsonMessage);
                message = messagesService.save(message);
                newMessagesIds.add(message.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        JsonElement response;
        if(jsonMessages.size() == newMessagesIds.size()) {
            response = GsonConverter.collectionToJsonArray(newMessagesIds);
        } else if(newMessagesIds.size() > 0) {
            response = new JsonPrimitive("PARTLY_FAIL");
        } else {
            response = new JsonPrimitive("FAIL");
        }
        return response;
    }

    private Message parseMessageFromJson(JsonObject jsonMessage) {
        Message message = new Message();
        message.setMessage(jsonMessage.get("message").getAsString());
        message.setDate(LocalDateTime.parse(jsonMessage.get("date").getAsString(), DateTimeFormatter.ISO_DATE_TIME));
        message.setUser(usersService.getByLogin(jsonMessage.get("userId").getAsString()));
        return message;
    }
}
