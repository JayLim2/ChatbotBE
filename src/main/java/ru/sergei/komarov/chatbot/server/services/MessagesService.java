package ru.sergei.komarov.chatbot.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sergei.komarov.chatbot.server.models.Message;
import ru.sergei.komarov.chatbot.server.models.User;
import ru.sergei.komarov.chatbot.server.repositories.MessagesRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessagesService {

    private final MessagesRepository messagesRepository;

    @Autowired
    public MessagesService(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    public List<Message> getAll() {
        return messagesRepository.findAll();
    }

    public Message getById(int id) {
        return messagesRepository.findById(id).orElse(null);
    }

    public List<Message> getByUser(User user) {
        return messagesRepository.findByUser(user);
    }

    public List<Message> getByDateBetween(LocalDateTime from, LocalDateTime to) {
        return messagesRepository.findByDateBetween(from, to);
    }

    public Message save(Message message) {
        return messagesRepository.save(message);
    }

    public Iterable<Message> saveAll(Iterable<Message> messages) {
        return messagesRepository.saveAll(messages);
    }

    public void delete(Message message) {
        messagesRepository.delete(message);
    }

    public void deleteById(int id) {
        messagesRepository.deleteById(id);
    }

    public void deleteAll() {
        messagesRepository.deleteAll();
    }

    public void deleteAll(Iterable<Message> messages) {
        messagesRepository.deleteAll(messages);
    }
}
