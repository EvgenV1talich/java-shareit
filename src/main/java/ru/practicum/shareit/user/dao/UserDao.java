package ru.practicum.shareit.user.dao;

import ru.practicum.shareit.user.User;

import java.util.List;

public interface UserDao {

    void create(User user);

    User read(Integer userId);

    void update(User user);

    void delete(Integer userId);

    List<User> getAll();


}
