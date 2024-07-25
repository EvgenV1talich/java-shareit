package ru.practicum.shareit.item.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.dao.UserDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Repository
public class ItemInMemoryDao implements ItemDao {

    private final UserDao userDao;

    private final Set<Integer> indexes = new TreeSet<>();
    private Integer lastAddedIndex;
    private final HashMap<Integer, Item> items = new HashMap<>();

    @Autowired
    public ItemInMemoryDao(UserDao userDao) {
        this.userDao = userDao;
        lastAddedIndex = 0;
        indexes.add(lastAddedIndex);
    }

    @Override
    public Item create(Item item, Integer ownerId) {
        if (item.getId() == null || !indexes.contains(item.getId())) {
            item.setId(getNewIndex());
            item.setAvailable(true);
            setOwner(item, ownerId);
        }
        items.put(item.getId(), item);
        return item;

    }

    @Override
    public Item read(Integer id) {
        checkIndex(id);
        return items.get(id);
    }

    @Override
    public Item update(Integer id, Item item) {
        checkIndex(id);
        items.replace(item.getId(), item);
        return items.get(item.getId());
    }

    @Override
    public void delete(Integer id) {
        checkIndex(id);
        items.remove(id);
    }

    @Override
    public List<Item> readAll() {
        return new ArrayList<>(items.values());
    }

    @Override
    public List<Item> readByUser(Integer userId) {
        List<Item> userItems = new ArrayList<>();
        return new ArrayList<>(items.values());
    }

    private void checkIndex(Integer newIndex) {
        if (indexes.contains(newIndex)) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Item not found!");
        }
    }

    private Integer getNewIndex() {
        Integer newIndex = ++lastAddedIndex;
        indexes.add(newIndex);
        return lastAddedIndex;
    }
    private void setOwner(Item item, Integer ownerId) {
        if (userDao.isUserExists(ownerId)) {
            item.setOwner(userDao.read(ownerId));
        } else {
            throw new ResponseStatusException(HttpStatus.valueOf(404));
        }
    }
}
