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

    private final Set<Long> indexes = new TreeSet<>();
    private Long lastAddedIndex;

    private HashMap<Long, String> emails = new HashMap<>();

    private final HashMap<Long, User> users = new HashMap<>();

    public UserInMemoryDao() {
        lastAddedIndex = 0L;
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
    public User read(Long userId) {
        checkIndex(userId);
        return users.get(userId);
    }

    @Override
    public User update(User user, Long id) {

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
    public void delete(Long userId) {
        checkIndex(userId);
        users.remove(userId);
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
    public boolean isUserExists(Long userId) {
        return indexes.contains(userId);
    }

    private void checkIndex(Long newIndex) {
        if (!indexes.contains(newIndex)) {
            throw new ItemNotFoundException("Ошибка создания Item " + newIndex);
        }
    }

    private Long getNewIndex() {
        Long newIndex = ++lastAddedIndex;
        indexes.add(newIndex);
        return lastAddedIndex;
    }

    private void updateEmail(Long id, String email) {
        if (emails.containsValue(email) && !users.get(id).getEmail().equals(email)) {
            throw new EmailExistsException("Такой email уже существует");
        } else {
            emails.remove(id);
            emails.put(id, email);
            users.get(id).setEmail(email);
        }
    }
}
