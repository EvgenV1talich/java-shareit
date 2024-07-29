package ru.practicum.shareit.user.dao;

import ru.practicum.shareit.user.User;

import java.util.List;


public interface UserDao {

    User create(User user);

    User read(Long userId);

    User update(User user, Long id);

    void delete(Long userId);

    List<User> getAll();

    boolean checkEmail(String email);

    boolean isUserExists(Long userId);


}
