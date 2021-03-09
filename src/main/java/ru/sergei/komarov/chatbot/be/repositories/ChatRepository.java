package ru.sergei.komarov.chatbot.be.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.sergei.komarov.chatbot.be.models.Chat;

public interface ChatRepository extends CrudRepository<Chat, Integer> {
}
