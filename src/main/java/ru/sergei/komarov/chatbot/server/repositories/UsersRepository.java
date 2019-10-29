package ru.sergei.komarov.chatbot.server.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import ru.sergei.komarov.chatbot.server.models.User;

import java.util.List;

@Component
public interface UsersRepository extends CrudRepository<User, String> {
    List<User> findAll();
    User findByLogin(String login);
    void deleteByLogin(String login);
    User findByLoginAndPassword(String login, String hashedPassword);
    boolean existsByLoginAndPassword(String login, String hashedPassword);
}
