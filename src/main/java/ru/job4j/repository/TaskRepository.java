package ru.job4j.repository;

import ru.job4j.model.Task;

import java.util.List;

public interface TaskRepository {

    List<Task> findAll();

    List<Task> findAllCompleted();

    List<Task> findAllNotCompleted();

    Task add(Task task);

    void update(Task task, int id);

    Task findById(int id);

    List<Task> findByName(String key);

    void delete(int id);

}
