package ru.practicum.shareit.user.dao;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.shareit.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class UserInMemoryDao implements UserDao {

    private final Set<Integer> indexes = new TreeSet<>();
    private Integer lastAddedIndex;

    private final HashMap<Integer, User> users = new HashMap<>();

    public UserInMemoryDao() {
        lastAddedIndex = 0;
        indexes.add(lastAddedIndex);
    }

    @Override
    public void create(User user) {
        checkIndex(user.getId());
        users.put(user.getId(), user);
    }

    @Override
    public User read(Integer userId) {
        checkIndex(userId);
        return users.get(userId);
    }

    @Override
    public void update(User user) {
        checkIndex(user.getId());
        users.replace(user.getId(), user);
    }

    @Override
    public void delete(Integer userId) {
        checkIndex(userId);
        users.remove(userId);
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }

    private void checkIndex(int newIndex) {
        if (indexes.contains(newIndex)) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Item not found!");
        }
    }

    private Integer getNewIndex() {
        Integer newIndex = ++lastAddedIndex;
        indexes.add(newIndex);
        return lastAddedIndex;
    }
}
