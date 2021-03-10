package ru.sergei.komarov.chatbot.be.services;

import org.springframework.stereotype.Service;
import ru.sergei.komarov.chatbot.be.exceptions.DataNotFoundException;
import ru.sergei.komarov.chatbot.be.models.Chat;
import ru.sergei.komarov.chatbot.be.models.User;
import ru.sergei.komarov.chatbot.be.repositories.ChatRepository;
import ru.sergei.komarov.chatbot.be.repositories.MessagesRepository;

import java.util.List;

@Service
public class ChatService implements BasicDataService<Chat, Integer> {

    private ChatRepository chatRepository;
    private MessagesRepository messagesRepository;

    public ChatService(ChatRepository chatRepository,
                       MessagesRepository messagesRepository) {

        this.chatRepository = chatRepository;
        this.messagesRepository = messagesRepository;
    }

    public Chat getFirstChatByUser(User user) {
        return chatRepository.findFirstByOwner(user);
    }

    public List<Chat> getChatsByUser(User user) {
        return chatRepository.findAllByOwner(user);
    }

    /*
    Технические методы
     */

    @Override
    public Chat getById(Integer id) throws DataNotFoundException {
        return chatRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Chat with id = " + id + " doesn't exists.")
        );
    }

    @Override
    public List<Chat> getAll() {
        return chatRepository.findAll();
    }

    @Override
    public Chat save(Chat item) {
        return chatRepository.save(item);
    }

    @Override
    public List<Chat> saveList(List<Chat> chats) {
        return (List<Chat>)chatRepository.saveAll(chats);
    }

    @Override
    public void deleteById(Integer integer) {
        chatRepository.deleteById(integer);
    }

    @Override
    public void delete(Chat item) {
        chatRepository.delete(item);
    }

    @Override
    public void deleteList(List<Chat> items) {
        chatRepository.deleteAll(items);
    }

    @Override
    public void deleteAll() {
        chatRepository.deleteAll();
    }
}
