package ru.job4j.service;

import ru.job4j.model.Priority;

import java.util.List;
import java.util.Optional;

public interface PriorityService {

    List<Priority> findAll();

    Priority add(Priority priority);

    Optional<Priority> findById(int id);

    List<Priority> findByName(String key);

    boolean delete(int id);
}
