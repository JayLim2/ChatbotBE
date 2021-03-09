package ru.sergei.komarov.chatbot.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sergei.komarov.chatbot.be.models.Chat;
import ru.sergei.komarov.chatbot.be.models.Message;
import ru.sergei.komarov.chatbot.be.repositories.MessagesRepository;

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

    public List<Message> getByChat(Chat chat) {
        return messagesRepository.findAllByChat(chat);
    }

    public void deleteAllByChat(Chat chat) {
        messagesRepository.deleteAllByChat(chat);
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
