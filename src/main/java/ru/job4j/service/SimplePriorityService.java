package ru.job4j.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.model.Priority;
import ru.job4j.repository.PriorityRepository;

import java.util.List;
import java.util.Optional;

@ThreadSafe
@Service
public class SimplePriorityService implements PriorityService {

    private final PriorityRepository store;

    public SimplePriorityService(PriorityRepository store) {
        this.store = store;
    }

    @Override
    public List<Priority> findAll() {
        return store.findAll();
    }

    @Override
    public Priority add(Priority priority) {
        return store.add(priority);
    }

    @Override
    public Optional<Priority> findById(int id) {
        return store.findById(id);
    }

    @Override
    public List<Priority> findByName(String key) {
        return store.findByName(key);
    }

    @Override
    public boolean delete(int id) {
        return store.delete(id);
    }
}
