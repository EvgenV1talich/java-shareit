package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.dao.UserDao;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    @Override
    public User create(UserDto user) {
        if (userDao.checkEmail(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.valueOf(409), "Такой e-mail уже используется!");
        }
        return userDao.create(UserMapper.dtotoUser(user));
    }

    @Override
    public User read(Integer userId) {
        return userDao.read(userId);
    }

    @Override
    public User update(UserDto user) {
        return userDao.update(UserMapper.dtotoUser(user));
    }

    @Override
    public void delete(Integer userId) {
        userDao.delete(userId);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }
}
