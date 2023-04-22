package ru.job4j.repository;

import ru.job4j.model.Task;
import ru.job4j.model.User;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    List<Task> findAll();

    List<Task> findAllByCondition(boolean done);

    Task add(Task task);

    boolean update(Task task, int id);

    Optional<Task> findById(int id);

    List<Task> findByName(String key);

    boolean delete(int id);

    boolean setDone(int id);

    boolean setUser(User user);

}
