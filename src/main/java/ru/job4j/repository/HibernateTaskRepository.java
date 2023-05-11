package ru.job4j.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Category;
import ru.job4j.model.Task;
import ru.job4j.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HibernateTaskRepository implements TaskRepository {

    private final HibernateCrudRepository crudRepository;

    @Override
    public List<Task> findAll() {
        return crudRepository.query("from  Task t join fetch t.priority "
                + "join fetch t.participates "
                + "order by t.id asc", Task.class);
    }

    @Override
    public List<Task> findAllByCondition(boolean done) {
        return crudRepository.query(
                "from Task t join fetch t.priority "
                        + "join fetch t.participates "
                        + "where done =: fDone order by t.id asc", Task.class,
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

    @Override
    public boolean setUser(User user) {
        crudRepository.run(
                "UPDATE Task SET user =: fUser",
                Map.of("fUser", user)
        );
        return true;
    }

}
