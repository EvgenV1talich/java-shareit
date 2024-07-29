package ru.practicum.shareit.user.dao;

import ru.practicum.shareit.user.User;

import java.util.List;


public interface UserDao {

    User create(User user);

    User read(Integer userId);

    User update(User user, Integer id);

    void delete(Integer userId);

    List<User> getAll();

    boolean checkEmail(String email);

    boolean isUserExists(Integer userId);


}
