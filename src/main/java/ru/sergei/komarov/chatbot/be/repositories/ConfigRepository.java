package ru.sergei.komarov.chatbot.be.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.sergei.komarov.chatbot.be.models.ProfileConfiguration;
import ru.sergei.komarov.chatbot.be.models.User;

public interface ConfigRepository extends CrudRepository<ProfileConfiguration, Integer> {
    ProfileConfiguration findByOwner(User user);
}
