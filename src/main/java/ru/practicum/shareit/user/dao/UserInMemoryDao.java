package ru.practicum.shareit.user.dao;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.shareit.exceptions.EmailExistsException;
import ru.practicum.shareit.exceptions.ItemNotFoundException;
import ru.practicum.shareit.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Repository
public class UserInMemoryDao implements UserDao {

    private final Set<Integer> indexes = new TreeSet<>();
    private Integer lastAddedIndex;

    private HashMap<Integer, String> emails = new HashMap<>();

    private final HashMap<Integer, User> users = new HashMap<>();

    public UserInMemoryDao() {
        lastAddedIndex = 0;
        indexes.add(lastAddedIndex);

    }

    @Override
    public User create(User user) {
        if (user.getId() == null || !indexes.contains(user.getId())) {
            user.setId(getNewIndex());
        }
        users.put(user.getId(), user);
        emails.put(user.getId(), user.getEmail());
        return user;
    }

    @Override
    public User read(Integer userId) {
        checkIndex(userId);
        return users.get(userId);
    }

    @Override
    public User update(User user, Integer id) {

        boolean isNewId = user.getId() != null;
        boolean isNewName = user.getName() != null;
        boolean isNewEmail = user.getEmail() != null;

        if (isNewId) {
            throw new ResponseStatusException(HttpStatus.valueOf(500));
        }
        user.setId(id);
        if (isNewName) {
            users.get(id).setName(user.getName());
        } else {
            user.setName(users.get(id).getName());
        }
        if (isNewEmail) {
            updateEmail(id, user.getEmail());
        } else {
            user.setEmail(users.get(id).getEmail());
        }
        return user;

    }

    @Override
    public void delete(Integer userId) {
        checkIndex(userId);
        users.remove(userId);
        emails.remove(userId);
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public boolean checkEmail(String email) {
        return emails.containsValue(email);
    }

    @Override
    public boolean isUserExists(Integer userId) {
        return indexes.contains(userId);
    }

    private void checkIndex(int newIndex) {
        if (!indexes.contains(newIndex)) {
            throw new ItemNotFoundException("Ошибка создания Item " + newIndex);
        }
    }

    private Integer getNewIndex() {
        Integer newIndex = ++lastAddedIndex;
        indexes.add(newIndex);
        return lastAddedIndex;
    }

    private void updateEmail(Integer id, String email) {
        if (emails.containsValue(email) && !users.get(id).getEmail().equals(email)) {
            throw new EmailExistsException("Такой email уже существует");
        } else {
            emails.remove(id);
            emails.put(id, email);
            users.get(id).setEmail(email);
        }
    }
}
