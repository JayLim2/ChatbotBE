package ru.sergei.komarov.chatbot.be.services;

import org.springframework.stereotype.Service;
import ru.sergei.komarov.chatbot.be.exceptions.DataNotFoundException;
import ru.sergei.komarov.chatbot.be.models.Topic;
import ru.sergei.komarov.chatbot.be.repositories.TopicRepository;

import java.util.List;

@Service
public class TopicService implements BasicDataService<Topic, Integer> {

    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public Topic getById(Integer integer) {
        return topicRepository.findById(integer).orElseThrow(() -> new DataNotFoundException(
                "Topic with id = " + integer + " not found."
        ));
    }

    @Override
    public List<Topic> getAll() {
        return (List<Topic>)topicRepository.findAll();
    }

    @Override
    public Topic save(Topic item) {
        return topicRepository.save(item);
    }

    @Override
    public List<Topic> saveList(List<Topic> items) {
        return (List<Topic>)topicRepository.saveAll(items);
    }

    @Override
    public void deleteById(Integer integer) {
        topicRepository.deleteById(integer);
    }

    @Override
    public void delete(Topic item) {
        topicRepository.delete(item);
    }

    @Override
    public void deleteList(List<Topic> items) {
        topicRepository.deleteAll(items);
    }

    @Override
    public void deleteAll() {
        topicRepository.deleteAll();
    }
}
