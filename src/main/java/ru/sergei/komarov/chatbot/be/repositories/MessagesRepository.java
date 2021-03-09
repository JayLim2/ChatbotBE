package ru.sergei.komarov.chatbot.be.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import ru.sergei.komarov.chatbot.be.models.Chat;
import ru.sergei.komarov.chatbot.be.models.Message;
import ru.sergei.komarov.chatbot.be.models.User;

import java.time.LocalDateTime;
import java.util.List;

@Component
public interface  MessagesRepository extends CrudRepository<Message, Integer> {
    List<Message> findAll();
    List<Message> findAllByChat(Chat chat);
    void deleteAllByChat(Chat chat);
}
