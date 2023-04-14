package ru.job4j.repository;

import ru.job4j.model.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> add(User user);

    Optional<User> findUserByLoginPassword(String name, String login, String password);

}
