package ru.sergei.komarov.chatbot.be.services;

import org.springframework.stereotype.Service;
import ru.sergei.komarov.chatbot.be.exceptions.DataNotFoundException;
import ru.sergei.komarov.chatbot.be.models.ProfileConfiguration;
import ru.sergei.komarov.chatbot.be.models.User;
import ru.sergei.komarov.chatbot.be.repositories.ConfigRepository;

import java.util.List;

@Service
public class ConfigService implements BasicDataService<ProfileConfiguration, Integer> {

    private final ConfigRepository configRepository;

    public ConfigService(ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    @Override
    public ProfileConfiguration getById(Integer integer) {
        return configRepository.findById(integer).orElseThrow(() -> new DataNotFoundException(
                "Config with id = " + integer + " not found."
        ));
    }

    @Override
    public List<ProfileConfiguration> getAll() {
        return (List<ProfileConfiguration>)configRepository.findAll();
    }

    @Override
    public ProfileConfiguration save(ProfileConfiguration item) {
        return configRepository.save(item);
    }

    @Override
    public List<ProfileConfiguration> saveList(List<ProfileConfiguration> items) {
        return (List<ProfileConfiguration>)configRepository.saveAll(items);
    }

    @Override
    public void deleteById(Integer integer) {
        configRepository.deleteById(integer);
    }

    @Override
    public void delete(ProfileConfiguration item) {
        configRepository.delete(item);
    }

    @Override
    public void deleteList(List<ProfileConfiguration> items) {
        configRepository.deleteAll(items);
    }

    @Override
    public void deleteAll() {
        configRepository.deleteAll();
    }

    public ProfileConfiguration getConfigByUser(User user) {
        return configRepository.findByOwner(user);
    }
}
