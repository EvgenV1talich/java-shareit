package ru.practicum.shareit.item.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.exceptions.ItemNotFoundException;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Repository
public class ItemInMemoryDao implements ItemDao {

    private final Set<Long> indexes = new TreeSet<>();
    private Long lastAddedIndex;
    private final HashMap<Long, Item> items = new HashMap<>();

    @Autowired
    public ItemInMemoryDao() {
        lastAddedIndex = 0L;
        indexes.add(lastAddedIndex);
    }

    @Override
    public Item create(Item item, User owner) {
        if (item.getId() == null || !indexes.contains(item.getId())) {
            item.setAvailable(item.getAvailable());
            setOwner(item, owner);
            item.setId(getNewIndex());
        }
        items.put(item.getId(), item);
        return item;

    }

    @Override
    public Item read(Long id) {
        checkIndex(id);
        return items.get(id);
    }

    @Override
    public Item update(Long id, Item item, Long requestUserId, User owner) {
        boolean isNewName = item.getName() != null;
        boolean isNewDescription = item.getDescription() != null;
        boolean isNewAvailable = item.getAvailable() != null;

        if (isNewName) {
            items.get(id).setName(item.getName());
        }
        if (isNewDescription) {
            items.get(id).setDescription(item.getDescription());
        }
        if (isNewAvailable) {
            items.get(id).setAvailable(item.getAvailable());
        }
        item.setOwner(owner);
        items.replace(item.getId(), item);
        return items.get(id);
    }

    @Override
    public void delete(Long id) {
        checkIndex(id);
        items.remove(id);
    }

    @Override
    public List<Item> readAll() {
        return new ArrayList<>(items.values());
    }

    @Override
    public List<Item> readByUser(Long userId) {
        return items.values().stream()
                .filter(item -> item.getOwner()
                        .getId()
                        .equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> searchItems(String request) {
        List<Item> availableItems = items.values().stream().filter(Item::getAvailable).toList();

        return availableItems.stream()
                .filter(item -> item.getName().toLowerCase().contains(request.toLowerCase())
                        || item.getDescription().toLowerCase().contains(request.toLowerCase()))
                .collect(Collectors.toList());
    }

    private void checkIndex(Long newIndex) {
        if (!indexes.contains(newIndex)) {
            throw new ItemNotFoundException("Такой item не найден");
        }
    }

    private Long getNewIndex() {
        Long newIndex = ++lastAddedIndex;
        indexes.add(newIndex);
        return lastAddedIndex;
    }

    private void setOwner(Item item, User owner) {
        item.setOwner(owner);
    }
}
