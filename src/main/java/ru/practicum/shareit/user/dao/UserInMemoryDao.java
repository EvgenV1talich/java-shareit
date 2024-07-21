package ru.practicum.shareit.user.dao;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.shareit.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

@Repository
public class UserInMemoryDao implements UserDao {

    private final Set<Integer> indexes = new TreeSet<>();
    private Integer lastAddedIndex;

    private List<String> emails = new ArrayList<>();

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
        emails.add(user.getEmail());
        return user;
    }

    @Override
    public User read(Integer userId) {
        checkIndex(userId);
        return users.get(userId);
    }

    @Override
    public User update(User user) {
        checkIndex(user.getId());
        users.replace(user.getId(), user);
        return user;
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

    @Override
    public boolean checkEmail(String email) {
        return emails.contains(email);
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
