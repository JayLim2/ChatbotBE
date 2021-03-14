package ru.sergei.komarov.chatbot.be.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.sergei.komarov.chatbot.be.models.Topic;

public interface TopicRepository extends CrudRepository<Topic, Integer> {
}
