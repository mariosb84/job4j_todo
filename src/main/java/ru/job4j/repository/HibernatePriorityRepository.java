package ru.job4j.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Priority;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HibernatePriorityRepository implements PriorityRepository  {

    private final HibernateCrudRepository crudRepository;

    @Override
    public List<Priority> findAll() {
        return crudRepository.query("from  Priority order by id asc", Priority.class);
    }

    @Override
    public Priority add(Priority priority) {
        crudRepository.run(session -> session.persist(priority));
        return priority;
    }

    @Override
    public Optional<Priority> findById(int id) {
        return crudRepository.optional(
                "from Priority where id = :fId", Priority.class,
                Map.of("fId", id)
        );
    }

    @Override
    public List<Priority> findByName(String key) {
        return crudRepository.query(
                "from Priority where name = :fName", Priority.class,
                Map.of("fName", key)
        );
    }

    @Override
    public boolean delete(int id) {
        crudRepository.run(
                "delete from Priority where id = :fId",
                Map.of("fId", id)
        );
        return true;
    }

}
