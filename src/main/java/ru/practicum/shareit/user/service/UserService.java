package ru.practicum.shareit.user.service;

import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;

public interface UserService {

    User create(UserDto user);

    User read(Long userId);

    User update(UserDto user, Long id);

    void delete(Long userId);

    List<User> getAll();

}
