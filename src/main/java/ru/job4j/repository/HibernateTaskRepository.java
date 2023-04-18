package ru.job4j.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Task;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HibernateTaskRepository implements TaskRepository {

    private final HibernateCrudRepository crudRepository;

    @Override
    public List<Task> findAll() {
        return crudRepository.query("from Task  order by id asc", Task.class);
    }

    @Override
    public List<Task> findAllByCondition(boolean done) {
        return crudRepository.query(
                "from Task where done =: fDone order by id", Task.class,
                Map.of("fDone", done)
        );
    }

    @Override
    public Task add(Task task) {
        crudRepository.run(session -> session.persist(task));
        return task;
    }

    @Override
    public boolean update(Task task, int id) {
        crudRepository.run(session -> session.merge(task));
        return true;
    }

    @Override
    public Optional<Task> findById(int id) {
        return crudRepository.optional(
                "from Task where id = :fId", Task.class,
                Map.of("fId", id)
        );
    }

    @Override
    public List<Task> findByName(String key) {
        return crudRepository.query(
                "from Task where description = :fName", Task.class,
                Map.of("fName", key)
        );
    }

    @Override
    public boolean delete(int id) {
        crudRepository.run(
                "delete from Task where id = :fId",
                Map.of("fId", id)
        );
        return true;
    }

    @Override
    public boolean setDone(int id) {
        crudRepository.run(
                "UPDATE Task SET done =: fDone WHERE id = :fId",
                Map.of("fId", id, "fDone", true)
        );
        return true;
    }

}
