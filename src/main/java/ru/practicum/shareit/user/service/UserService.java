package ru.practicum.shareit.user.service;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.User;

import java.util.List;

@Service
public interface UserService {

    void create(User user);

    User read(Integer userId);

    void update(User user);

    void delete(Integer userId);

    List<User> getAll();

}
