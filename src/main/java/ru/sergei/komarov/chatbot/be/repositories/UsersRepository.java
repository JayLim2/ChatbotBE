package ru.sergei.komarov.chatbot.be.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import ru.sergei.komarov.chatbot.be.models.User;

import java.util.List;

@Component
public interface UsersRepository extends CrudRepository<User, String> {
    List<User> findAll();
    User findByLogin(String login);
    void deleteByLogin(String login);
    User findByLoginAndPasswordHash(String login, String hashedPassword);
    boolean existsByLoginAndPasswordHash(String login, String hashedPassword);
}
