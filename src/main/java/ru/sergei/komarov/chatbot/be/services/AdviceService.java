package ru.sergei.komarov.chatbot.be.services;

import org.springframework.stereotype.Service;
import ru.sergei.komarov.chatbot.be.exceptions.DataNotFoundException;
import ru.sergei.komarov.chatbot.be.models.Advice;
import ru.sergei.komarov.chatbot.be.repositories.AdviceRepository;

import java.util.List;

@Service
public class AdviceService implements BasicDataService<Advice, Integer> {

    private final AdviceRepository adviceRepository;

    public AdviceService(AdviceRepository adviceRepository) {
        this.adviceRepository = adviceRepository;
    }

    @Override
    public Advice getById(Integer integer) {
        return adviceRepository.findById(integer).orElseThrow(() -> new DataNotFoundException(
                "Advice with id = " + integer + " not found."
        ));
    }

    @Override
    public List<Advice> getAll() {
        return (List<Advice>)adviceRepository.findAll();
    }

    @Override
    public Advice save(Advice item) {
        return adviceRepository.save(item);
    }

    @Override
    public List<Advice> saveList(List<Advice> items) {
        return (List<Advice>)adviceRepository.saveAll(items);
    }

    @Override
    public void deleteById(Integer integer) {
        adviceRepository.deleteById(integer);
    }

    @Override
    public void delete(Advice item) {
        adviceRepository.delete(item);
    }

    @Override
    public void deleteList(List<Advice> items) {
        adviceRepository.deleteAll(items);
    }

    @Override
    public void deleteAll() {
        adviceRepository.deleteAll();
    }
}
