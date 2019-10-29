package ru.sergei.komarov.chatbot.server.utils;

import ru.sergei.komarov.chatbot.server.models.Message;
import ru.sergei.komarov.chatbot.server.models.User;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Converter {

    public static Map<String, Object> userToMap(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("login", user.getLogin());
        map.put("password", user.getPassword());
        map.put("messages", messagesToMap(user.getMessages()));
        return map;
    }

    public static List<Map<String, Object>> usersToMap(List<User> usersList) {
        List<Map<String, Object>> users = new ArrayList<>();
        for (User user : usersList) {
            users.add(userToMap(user));
        }
        return users;
    }

    public static List<Map<String, Object>> messagesToMap(List<Message> messagesList) {
        List<Map<String, Object>> messages = new ArrayList<>();
        for (Message message : messagesList) {
            messages.add(messageToMap(message));
        }
        return messages;
    }

    public static Map<String, Object> messageToMap(Message message) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", message.getId());
        map.put("message", message.getMessage());
        map.put("userId", message.getUser().getLogin());
        map.put("date", DateTimeFormatter.ISO_DATE_TIME.format(message.getDate()));
        return map;
    }
}
