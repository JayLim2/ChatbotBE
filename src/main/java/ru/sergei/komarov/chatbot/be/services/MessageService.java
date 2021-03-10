package ru.sergei.komarov.chatbot.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sergei.komarov.chatbot.be.exceptions.DataNotFoundException;
import ru.sergei.komarov.chatbot.be.models.Chat;
import ru.sergei.komarov.chatbot.be.models.Message;
import ru.sergei.komarov.chatbot.be.repositories.MessagesRepository;

import java.util.List;

@Service
public class MessageService implements BasicDataService<Message, Integer> {

    private final MessagesRepository messagesRepository;

    @Autowired
    public MessageService(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    @Override
    public Message getById(Integer id) {
        return messagesRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Message with id = " + id + " doesn't exists.")
        );
    }

    @Override
    public List<Message> getAll() {
        return messagesRepository.findAll();
    }

    @Override
    public Message save(Message message) {
        return messagesRepository.save(message);
    }

    @Override
    public List<Message> saveList(List<Message> items) {
        return (List<Message>)messagesRepository.saveAll(items);
    }

    @Override
    public void deleteById(Integer id) {
        messagesRepository.deleteById(id);
    }

    @Override
    public void delete(Message message) {
        messagesRepository.delete(message);
    }

    @Override
    public void deleteList(List<Message> items) {
        messagesRepository.deleteAll(items);
    }

    @Override
    public void deleteAll() {
        messagesRepository.deleteAll();
    }

    public List<Message> getByChat(Chat chat) {
        return messagesRepository.findAllByChat(chat);
    }

    public void deleteAllByChat(Chat chat) {
        messagesRepository.deleteAllByChat(chat);
    }

}
