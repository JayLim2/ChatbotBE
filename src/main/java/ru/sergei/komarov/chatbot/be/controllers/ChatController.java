package ru.sergei.komarov.chatbot.be.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sergei.komarov.chatbot.be.models.Chat;
import ru.sergei.komarov.chatbot.be.models.User;
import ru.sergei.komarov.chatbot.be.services.ChatService;
import ru.sergei.komarov.chatbot.be.services.MessageService;

import java.util.Base64;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;
    private final MessageService messageService;

    public ChatController(ChatService chatService, MessageService messageService) {
        this.chatService = chatService;
        this.messageService = messageService;
    }

    @GetMapping("/loadChatsForCurrentUser")
    public List<Chat> loadChatsForCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User)auth.getPrincipal();
        List<Chat> chats = chatService.getChatsByUser(currentUser);
        if (chats.size() > 0) {
            for (Chat chat : chats) {
                System.out.printf("Chat #%d, messages count: %d", chat.getId(), chat.getMessages().size());
            }
        } else {
            Chat chat = new Chat();
            chat.setOwner(currentUser);
            chatService.save(chat);
            chats = Collections.singletonList(chat);
        }
        return chats;
    }

    @GetMapping("/loadFirstChatForCurrentUser")
    public Chat loadFirstChatForCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User)auth.getPrincipal();
        Chat chat = chatService.getFirstChatByUser(currentUser);
        System.out.printf("Chat #%d, messages count: %d", chat.getId(), chat.getMessages().size());
        return chat;
    }

}
