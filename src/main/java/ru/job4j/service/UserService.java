package ru.job4j.service;

import ru.job4j.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> add(User user);

    Optional<User> findUserByLoginPassword(String name, String login, String password);
}
