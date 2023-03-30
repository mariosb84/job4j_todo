package ru.job4j.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.model.Task;
import ru.job4j.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@ThreadSafe
@Service
public class SimpleTaskService implements TaskService {

    private final TaskRepository store;

    public SimpleTaskService(TaskRepository store) {
        this.store = store;
    }

    @Override
    public List<Task> findAll() {
        return store.findAll();
    }

    @Override
    public List<Task> findAllCompleted() {
        return store.findAllCompleted();
    }

    @Override
    public List<Task> findAllNotCompleted() {
        return store.findAllNotCompleted();
    }

    @Override
    public Optional<Task> add(Task task) {
        return store.add(task);
    }

    @Override
    public void update(Task task, int id) {
    store.update(task, id);
    }

    @Override
    public Optional<Task> findById(int id) {
        return store.findById(id);
    }

    @Override
    public List<Task> findByName(String key) {
        return store.findByName(key);
    }

    @Override
    public void delete(int id) {
    store.delete(id);
    }

}
