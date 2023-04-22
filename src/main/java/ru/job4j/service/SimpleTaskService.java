package ru.job4j.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.model.Task;
import ru.job4j.model.User;
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
    public List<Task> findAllByCondition(boolean done) {
        return store.findAllByCondition(done);
    }

    @Override
    public Task add(Task task) {
        return store.add(task);
    }

    @Override
    public boolean update(Task task, int id) {
     return store.update(task, id);
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
    public boolean delete(int id) {
    return store.delete(id);
    }

    @Override
    public boolean setDone(int id) {
        return store.setDone(id);
    }

    @Override
    public boolean setUser(User user) {
        return store.setUser(user);
    }

}
