package ru.sergei.komarov.chatbot.be.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.sergei.komarov.chatbot.be.models.Advice;

public interface AdviceRepository extends CrudRepository<Advice, Integer> {
}
