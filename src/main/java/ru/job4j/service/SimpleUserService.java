package ru.job4j.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.model.User;
import ru.job4j.repository.UserRepository;

import java.util.Optional;

@ThreadSafe
@Service
public class SimpleUserService implements UserService {

    private final UserRepository store;

    public SimpleUserService(UserRepository store) {
        this.store = store;
    }

    @Override
    public Optional<User> add(User user) {
        return store.add(user);
    }

    @Override
    public Optional<User> findUserByLoginPassword(String name, String login, String password) {
        return store.findUserByLoginPassword(name, login, password);
    }

}
