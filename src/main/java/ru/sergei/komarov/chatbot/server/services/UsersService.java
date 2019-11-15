package ru.sergei.komarov.chatbot.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sergei.komarov.chatbot.server.models.User;
import ru.sergei.komarov.chatbot.server.repositories.UsersRepository;
import ru.sergei.komarov.chatbot.server.utils.HashTool;

import java.util.List;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<User> getAll() {
        return usersRepository.findAll();
    }

    public User getByLogin(String login) {
        return usersRepository.findByLogin(login);
    }

    public User save(User user) {
        return usersRepository.save(user);
    }

    public Iterable<User> saveAll(Iterable<User> users) {
        return usersRepository.saveAll(users);
    }

    public void delete(User user) {
        usersRepository.delete(user);
    }

    public void deleteByLogin(String login) {
        usersRepository.deleteByLogin(login);
    }

    public void deleteAll() {
        usersRepository.deleteAll();
    }

    public void deleteAll(Iterable<User> users) {
        usersRepository.deleteAll(users);
    }

    public boolean validateCredentials(String login, String password) {
        System.out.println("Login: " + login);
        System.out.println("Password: " + password);
        System.out.println();

        if (HashTool.isNullOrEmptyString(login) || HashTool.isNullOrEmptyString(password)) {
            System.out.println("\tNull or empty login or password");
            return false;
        }

        String hashedPassword = HashTool.hash(password);
        System.out.println("hashed: " + hashedPassword);

        return usersRepository.existsByLoginAndPassword(login, hashedPassword);
    }
}
