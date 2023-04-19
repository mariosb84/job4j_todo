package ru.job4j.repository;


import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.model.User;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HibernateUserRepository implements UserRepository {

    private final HibernateCrudRepository crudRepository;

    private static final Logger LOG_USER = LoggerFactory.getLogger(HibernateUserRepository.class.getName());

    @Override
    public Optional<User> add(User user) {
        Optional<User> result = Optional.empty();
        try {
            crudRepository.run(session -> session.persist(user));
            result = Optional.of(user);
        } catch (Exception e) {
        LOG_USER.error("Exception in  add() method", e);
    }
        return result;
    }

    @Override
    public Optional<User> findUserByLoginPassword(String name, String login, String password) {
        return crudRepository.optional(
                "from User where name = :fName AND login = :fLogin AND password = :fPassword", User.class,
                Map.of("fName", name, "fLogin", login, "fPassword", password)
        );
    }

}
