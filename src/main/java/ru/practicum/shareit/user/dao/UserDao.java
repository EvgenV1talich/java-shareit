package ru.practicum.shareit.user.dao;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.user.User;

import java.util.List;

@Repository
public interface UserDao {

    User create(User user);

    User read(Integer userId);

    User update(User user);

    void delete(Integer userId);

    List<User> getAll();

    boolean checkEmail(String email);


}
