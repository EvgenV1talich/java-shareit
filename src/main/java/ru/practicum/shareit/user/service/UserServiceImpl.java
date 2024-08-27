package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exceptions.user.EmailExistsException;
import ru.practicum.shareit.exceptions.user.UserNotFoundException;
import ru.practicum.shareit.user.dao.UserPSQLDao;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;

import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
public class UserServiceImpl implements UserService {

    private final UserPSQLDao dao;

    @Override
    public User create(UserDto user) {
        if (isEmailUnique(user.getEmail())) {
            return dao.save(UserMapper.toUser(user));
        } else {
            throw new EmailExistsException("Email already exists!");
        }
    }

    @Override
    public User read(Long userId) {
        if (!dao.existsById(userId)) {
            throw new UserNotFoundException("Ошибка при чтении user (id = " + userId + ")");
        }
        return dao.getReferenceById(userId);
    }

    @Override
    public User update(UserDto user, Long id) {

        boolean isNewName = user.getName() != null;
        boolean isNewEmail = user.getEmail() != null;
        if (isNewEmail) {
            if (!isEmailUnique(user.getEmail()))
                throw new EmailExistsException("Ошибка при обновлении пользователя (email уже существует");
        }
        User userToUpdate = UserMapper.toUser(user);
        User oldUser = read(id);
        userToUpdate.setId(id);
        //Name update
        if (isNewName) {
            userToUpdate.setName(user.getName());
        } else {
            userToUpdate.setName(oldUser.getName());
        }
        //EmailUpdate
        if (isNewEmail) {
            userToUpdate.setEmail(user.getEmail());
        } else {
            userToUpdate.setEmail(oldUser.getEmail());
        }
        return dao.save(userToUpdate);

    }

    @Override
    public void delete(Long userId) {
        dao.deleteById(userId);
    }

    @Override
    public List<User> getAll() {
        return dao.findAll();
    }

    private boolean isEmailUnique(String email) {
        return dao.findByEmail(email).isEmpty();
    }
}
