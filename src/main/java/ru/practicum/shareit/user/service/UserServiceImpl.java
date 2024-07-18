package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.dao.UserDao;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    @Override
    public void create(User user) {
        userDao.create(user);
    }

    @Override
    public User read(Integer userId) {
        return userDao.read(userId);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
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
