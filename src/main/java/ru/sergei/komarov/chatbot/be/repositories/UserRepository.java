package ru.sergei.komarov.chatbot.be.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import ru.sergei.komarov.chatbot.be.models.User;

import java.util.List;

@Component
public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findAll();
    User findByLogin(String login);
    void deleteByLogin(String login);
    boolean existsByLoginAndPasswordHash(String login, String hashedPassword);
    boolean existsByLoginIgnoreCaseOrEmailIgnoreCase(String login, String email);
}
