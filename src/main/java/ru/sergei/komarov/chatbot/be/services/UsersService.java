package ru.sergei.komarov.chatbot.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.sergei.komarov.chatbot.be.exceptions.DataNotFoundException;
import ru.sergei.komarov.chatbot.be.models.User;
import ru.sergei.komarov.chatbot.be.repositories.UsersRepository;
import ru.sergei.komarov.chatbot.be.utils.HashTool;

import java.util.List;

@Service
public class UsersService implements BasicDataService<User, Integer>, UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public User getById(Integer id) {
        return usersRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("User with id = " + id + " doesn't exists.")
        );
    }

    @Override
    public List<User> getAll() {
        return usersRepository.findAll();
    }

    @Override
    public User save(User user) {
        return usersRepository.save(user);
    }

    @Override
    public List<User> saveList(List<User> items) {
        return (List<User>)usersRepository.saveAll(items);
    }

    @Override
    public void deleteById(Integer id) {
        usersRepository.deleteById(id);
    }

    @Override
    public void delete(User user) {
        usersRepository.delete(user);
    }

    @Override
    public void deleteList(List<User> items) {
        usersRepository.deleteAll(items);
    }

    @Override
    public void deleteAll() {
        usersRepository.deleteAll();
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return usersRepository.findByLogin(login);
    }

    public void deleteByLogin(String login) {
        usersRepository.deleteByLogin(login);
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

        return usersRepository.existsByLoginAndPasswordHash(login, hashedPassword);
    }
}
