package ru.sergei.komarov.chatbot.be.services;

import java.util.List;

public interface BasicDataService<T, ID> {

    T getById(ID id);

    List<T> getAll();

    T save(T item);

    List<T> saveList(List<T> items);

    void deleteById(ID id);

    void delete(T item);

    void deleteList(List<T> items);

    void deleteAll();
}
