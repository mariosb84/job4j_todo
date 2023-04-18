package ru.job4j.repository;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.model.User;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HibernateUserRepository implements UserRepository {

    private final HibernateCrudRepository crudRepository;

    @Override
    public Optional<User> add(User user) {
        crudRepository.run(session -> session.persist(user));
        return  Optional.of(user);
    }

    @Override
    public Optional<User> findUserByLoginPassword(String name, String login, String password) {
        return crudRepository.optional(
                "from User where name = :fName AND login = :fLogin AND password = :fPassword", User.class,
                Map.of("fName", name, "fLogin", login, "fPassword", password)
        );
    }

}
