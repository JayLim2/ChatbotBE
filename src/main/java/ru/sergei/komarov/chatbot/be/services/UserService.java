package ru.sergei.komarov.chatbot.be.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.sergei.komarov.chatbot.be.exceptions.DataNotFoundException;
import ru.sergei.komarov.chatbot.be.models.Role;
import ru.sergei.komarov.chatbot.be.models.User;
import ru.sergei.komarov.chatbot.be.repositories.UserRepository;

import java.util.List;

@Service
public class UserService implements BasicDataService<User, Integer>, UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("User with id = " + id + " doesn't exists.")
        );
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        if (user.getRole() == null) {
            user.setRole(Role.STUDENT);
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> saveList(List<User> items) {
        return (List<User>) userRepository.saveAll(items);
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void deleteList(List<User> items) {
        userRepository.deleteAll(items);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.findByLogin(login);
    }

    public void deleteByLogin(String login) {
        userRepository.deleteByLogin(login);
    }

    public boolean isAlreadyRegistered(User user) {
        return userRepository.existsByLoginIgnoreCaseOrEmailIgnoreCase(
                user.getLogin(), user.getEmail()
        );
    }

}
