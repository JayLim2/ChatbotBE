package ru.sergei.komarov.chatbot.server.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import ru.sergei.komarov.chatbot.server.models.Message;
import ru.sergei.komarov.chatbot.server.models.User;

import java.time.LocalDateTime;
import java.util.List;

@Component
public interface MessagesRepository extends CrudRepository<Message, Integer> {
    List<Message> findAll();
    List<Message> findByUser(User user);
    List<Message> findByDateBetween(LocalDateTime from, LocalDateTime to);
}
