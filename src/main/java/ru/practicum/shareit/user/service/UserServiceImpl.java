package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exceptions.EmailExistsException;
import ru.practicum.shareit.exceptions.FailInputParamsException;
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
            throw new EmailExistsException("Email уже используется");
        }
        if (user.getEmail() == null) {
            throw new FailInputParamsException("Передан пустой параметр e-mail!");
        }
        return userDao.create(UserMapper.dtotoUser(user));
    }

    @Override
    public User read(Long userId) {
        return userDao.read(userId);
    }

    @Override
    public User update(UserDto user, Long id) {
        return userDao.update(UserMapper.dtotoUser(user), id);
    }

    @Override
    public void delete(Long userId) {
        userDao.delete(userId);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }
}
