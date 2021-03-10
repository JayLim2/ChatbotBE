package ru.sergei.komarov.chatbot.be.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.sergei.komarov.chatbot.be.models.Chat;
import ru.sergei.komarov.chatbot.be.models.User;

import java.util.List;

public interface ChatRepository extends CrudRepository<Chat, Integer> {
    List<Chat> findAllByOwner(User user);
    Chat findFirstByOwner(User user);
    List<Chat> findAll();
}
