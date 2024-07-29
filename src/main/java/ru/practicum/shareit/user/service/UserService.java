package ru.practicum.shareit.user.service;

import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;


public interface UserService {

    User create(UserDto user);

    User read(Integer userId);

    User update(UserDto user, Integer id);

    void delete(Integer userId);

    List<User> getAll();

}
